package hu.bnpi.dhte.inventory.responsible.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "responsible")
    private List<InventoryItem> items;
}
