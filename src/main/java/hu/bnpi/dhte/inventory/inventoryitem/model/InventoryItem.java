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
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "inventory_id", length = 50, nullable = false)
    private String inventoryId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "item_type", length = 16, nullable = false)
    private ItemType itemType;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @PositiveOrZero
    @Column(name = "start_unit_price", scale = 2)
    private double startUnitPrice;

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
}
