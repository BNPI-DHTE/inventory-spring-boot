package hu.bnpi.dhte.inventory.dtos;

import hu.bnpi.dhte.inventory.models.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInventoryItemCommand {

    private Long id;

    @NonNull
    private String inventoryId;

    @NonNull
    private ItemType itemType;

    @NonNull
    private String name;

    private String description;

    private String serialNumber;

    private int amount;

    public CreateInventoryItemCommand(String inventoryId, String itemType, String name) {
        this.inventoryId = inventoryId;
        this.itemType = ItemType.valueOf(itemType);
        this.name = name;
    }
}
