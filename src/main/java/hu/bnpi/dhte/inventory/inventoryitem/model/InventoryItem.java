package hu.bnpi.dhte.inventory.inventoryitem.model;

import hu.bnpi.dhte.inventory.event.model.Event;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

//TODO Refactor this class and its Dto classes into multiple embeddable classes!

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "inventory_id", length = 50, nullable = false, unique = true)
    private String inventoryId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "item_type", length = 16)
    private ItemType itemType;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(name = "date_of_use")
    private LocalDate dateOfUse;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(length = 100, nullable = false)
    private String category;

    //TODO We'll need ManyToMany relation!
    @ManyToOne
    @JoinColumn(name = "kit_id")
    private Kit kit;

    @Column(name = "additional_fields", columnDefinition = "TEXT")
    private String additionalFields;

    @Column(length = 100)
    private String location;

    @Column(name = "to_disposal")
    private boolean toDisposal;

    private boolean deficit;

    @PositiveOrZero
    @Column(name = "initial_price", scale = 2)
    private double initialPrice;

    @PositiveOrZero
    private int amount;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "responsible_id")
    private Responsible responsible;

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "inventory_items_events",
    joinColumns = @JoinColumn(name = "inventory_item_id"),
    inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;

    public InventoryItem(String inventoryId, ItemType itemType, String name, LocalDate dateOfUse, String description, String serialNumber, String category,
                         String additionalFields, String location, boolean toDisposal, boolean deficit, double initialPrice, int amount) {
        this.inventoryId = inventoryId;
        this.itemType = itemType;
        this.name = name;
        this.dateOfUse = dateOfUse;
        this.description = description;
        this.serialNumber = serialNumber;
        this.category = category;
        this.additionalFields = additionalFields;
        this.location = location;
        this.toDisposal = toDisposal;
        this.deficit = deficit;
        this.initialPrice = initialPrice;
        this.amount = amount;
    }

    public InventoryItem(String inventoryId, String name, LocalDate dateOfUse, String description, String serialNumber, String additionalFields, int amount) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.dateOfUse = dateOfUse;
        this.description = description;
        this.serialNumber = serialNumber;
        this.additionalFields = additionalFields;
        this.amount = amount;
    }

    public void removeKit() {
        this.kit = null;
    }
}
