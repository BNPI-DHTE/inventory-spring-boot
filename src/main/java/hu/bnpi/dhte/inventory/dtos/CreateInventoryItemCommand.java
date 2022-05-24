package hu.bnpi.dhte.inventory.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateInventoryItemCommand {

    @NonNull
    @Schema(description = "inventory id of item", example = "AB1548")
    private String inventoryId;

    @NonNull
    @Schema(description = "type of item based on legislation categories in english with MACRO_CASE",
            allowableValues = {"HIGH_VALUE_ASSET", "LOW_VALUE_ASSET", "CONSUMABLE", "REAL_ESTATE", "INVESTMENT"})
    private String itemType;

    @NonNull
    @Schema(description = "short name of item", example = "laptop computer")
    private String name;

    @Schema(description = "detailed description of item, eg. manufacturer, type, model, etc.", example = "Lenovo Legion 5")
    private String description;

    @Schema(example = "154896AB-456870C")
    private String serialNumber;

    @NonNull
    @Schema(example = "1")
    private int amount;

    @Schema(description = "type of responsible", allowableValues = {"EMPLOYEE", "DEPARTMENT"})
    private String responsibleType;

    @Schema(description = "employee or department id based on organizational structure microservice")
    private Long responsibleId;
}
