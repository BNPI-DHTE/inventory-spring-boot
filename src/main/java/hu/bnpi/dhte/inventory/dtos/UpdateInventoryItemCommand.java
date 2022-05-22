package hu.bnpi.dhte.inventory.dtos;

import hu.bnpi.dhte.inventory.models.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInventoryItemCommand {

    private ItemType itemType;

    private String name;

    private String description;

    private String serialNumber;

    private int amount;
}
