package hu.bnpi.dhte.inventory.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory_item")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "inventory_id", unique = true, nullable = false, length = 50)
    private String inventoryId;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType itemType;

    @NonNull
    @Column(name = "item_name", nullable = false)
    private String name;

    private String description;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(nullable = false)
    private int amount;
}
