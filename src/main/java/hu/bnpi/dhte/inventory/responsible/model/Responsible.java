package hu.bnpi.dhte.inventory.responsible.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "responsible")
public abstract class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50, nullable = false, unique = true)
    private String responsibleId;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "responsible")
    private List<InventoryItem> items = new ArrayList<>();

    protected Responsible(String responsibleId, String name) {
        this.responsibleId = responsibleId;
        this.name = name;
    }

    protected Responsible(long id, String responsibleId, String name, List<InventoryItem> items) {
        this.id = id;
        this.responsibleId = responsibleId;
        this.name = name;
        this.items = items;
    }

    public void addInventoryItem(InventoryItem item) {
        this.items.add(item);
        item.setResponsible(this);
    }
}
