package hu.bnpi.dhte.inventory.inventoryitem.dtos;

import hu.bnpi.dhte.inventory.inventoryitem.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateInventoryItemCommand {

    //TODO Add messages to bean validations!

    private String inventoryNumber;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ItemType itemType;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @Size(max = 100)
    private String serialNumber;

    @NotBlank
    @Size(max = 100)
    private String category;

    private String additionalFields;

    @Size(max = 100)
    private String location;

    @PositiveOrZero
    private double initialPrice;

    @PositiveOrZero
    private int amount;
}
