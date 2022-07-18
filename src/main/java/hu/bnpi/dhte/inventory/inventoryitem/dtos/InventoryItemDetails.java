package hu.bnpi.dhte.inventory.inventoryitem.dtos;

import hu.bnpi.dhte.inventory.inventoryitem.model.ItemType;
import hu.bnpi.dhte.inventory.responsible.dtos.ResponsibleDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryItemDetails {

    private Long id;

    private String inventoryId;

    private ItemType itemType;

    private String name;

    private String description;

    private String serialNumber;

    private String category;

    private KitSummary kit;

    private String additionalFields;

    private String location;

    private boolean toDisposal;

    private boolean deficit;

    private double initialPrice;

    private ResponsibleDetails responsible;
}
