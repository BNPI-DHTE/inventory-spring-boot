package hu.bnpi.dhte.inventory.inventoryitem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "kits")
public class Kit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "kit")
    private List<InventoryItem> items = new ArrayList<>();

    public Kit(String name) {
        this.name = name;
    }

    public void addInventoryItem (InventoryItem item) {
        items.add(item);
    }

    public void removeInventoryItem (InventoryItem item) {
        items.remove(item);
    }
}