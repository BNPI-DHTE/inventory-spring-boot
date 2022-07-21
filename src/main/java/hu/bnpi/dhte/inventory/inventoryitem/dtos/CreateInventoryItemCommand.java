package hu.bnpi.dhte.inventory.inventoryitem.dtos;

import hu.bnpi.dhte.inventory.inventoryitem.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateInventoryItemCommand {

    //TODO: Unique validation of Inventory Id!

    @NotBlank(message = "Inventory id cannot be blank!")
    @Size(max = 50, message = "Inventory id cannot be longer than 50 characters!")
    private String inventoryId;

    @NotNull(message = "Item type cannot be null!")
    private ItemType itemType;

    @NotBlank(message = "Inventory item's name cannot be blank!")
    @Size(max = 100, message = "Inventory item's name cannot be longer than 50 characters!")
    private String name;

    @NotBlank(message = "Date of use cannot be blank!")
    @PastOrPresent(message = "date of use cannot be in the future!")
    private LocalDate dateOfUse;

    private String description;

    @Size(max = 100, message = "Serial number cannot be longer than 100 characters!")
    private String serialNumber;

    @Size(max = 100, message = "Category cannot be longer than 100 characters!")
    private String category;

    private String additionalFields;

    @Size(max = 100, message = "Location cannot be longer than 50 characters!")
    private String location;

    private boolean toDisposal;

    private boolean deficit;

    @PositiveOrZero(message = "Initial price cannot be negative!")
    private double initialPrice;

    @PositiveOrZero(message = "Amount maust be positive or zero")
    private int amount;
}
