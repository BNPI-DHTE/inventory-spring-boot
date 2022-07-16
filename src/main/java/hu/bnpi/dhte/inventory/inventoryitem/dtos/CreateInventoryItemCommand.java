package hu.bnpi.dhte.inventory.inventoryitem.dtos;

import hu.bnpi.dhte.inventory.inventoryitem.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateInventoryItemCommand {

    private String inventoryId;

    @Enumerated(value = EnumType.STRING)
    private ItemType itemType;

    @Size(max = 100)
    private String name;

    private String description;

    @Size(max = 100)
    private String serialNumber;

    @Size(max = 100)
    private String category;

    private String additionalFields;

    @Size(max = 100)
    private String location;

    private boolean toDisposal;

    private boolean deficit;

    @PositiveOrZero
    private double startUnitPrice;
}
