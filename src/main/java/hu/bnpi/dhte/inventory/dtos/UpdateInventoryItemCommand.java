package hu.bnpi.dhte.inventory.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInventoryItemCommand {

    private String itemType;

    private String name;

    private String description;

    private String serialNumber;

    private int amount;

    private String responsibleType;

    private Long responsibleId;
}
