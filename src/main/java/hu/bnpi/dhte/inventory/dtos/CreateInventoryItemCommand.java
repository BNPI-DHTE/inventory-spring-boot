package hu.bnpi.dhte.inventory.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateInventoryItemCommand {

    private Long id;

    @NonNull
    private String inventoryId;

    @NonNull
    private String itemType;

    @NonNull
    private String name;

    private String description;

    private String serialNumber;

    @NonNull
    private int amount;

    private String responsibleType;

    private Long responsibleId;
}
