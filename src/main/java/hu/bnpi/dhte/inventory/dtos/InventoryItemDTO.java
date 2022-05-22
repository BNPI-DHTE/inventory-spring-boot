package hu.bnpi.dhte.inventory.dtos;

import hu.bnpi.dhte.inventory.models.ItemType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class InventoryItemDTO {

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
}
