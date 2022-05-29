package hu.bnpi.dhte.inventory.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private ResponsibleType responsibleType;

    @OneToMany
    private Set<InventoryItem> itemsResponsibleFor = new HashSet<>();

    public Responsible(ResponsibleType responsibleType) {
        this.responsibleType = responsibleType;
    }

    public abstract void fetchName();

    public abstract void fetchEmail();

    public void addInventoryItem(InventoryItem item) {
        itemsResponsibleFor.add(item);
    }

    public void addMultipleInventoryItems(Collection<InventoryItem> items) {
        itemsResponsibleFor.addAll(items);
    }
}
