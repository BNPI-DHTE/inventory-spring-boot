package hu.bnpi.dhte.inventory.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToMany
    @JoinColumn(name = "item_id")
    private List<InventoryItem> items;

    private Long oldResponsibleId;

    private Long newResponsibleId;

    public void validateOldResponsible() {
        validateResponsible(oldResponsibleId);
        List<InventoryItem> invalidItems = items.stream()
                .filter(item -> item.getResponsible().getId() != oldResponsibleId)
                .toList();
        if (!invalidItems.isEmpty()) {
            throw new IllegalArgumentException("Responsible doesn't have items: " + invalidItems);
        }
    }

    public void validateNewResponsible() {
        validateResponsible(newResponsibleId);
    }

    private void validateResponsible(Long responsibleId) {
        if (responsibleId <= 0) {
            throw new IllegalArgumentException("Responsible id must be larger than 0");
        }
//        We can implement it after Employee and Department Microservices implemented.
    }
}
