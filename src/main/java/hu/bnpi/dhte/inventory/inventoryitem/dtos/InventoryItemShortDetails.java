package hu.bnpi.dhte.inventory.inventoryitem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryItemShortDetails {

    private String inventoryNumber;

    private String name;

    private int amount;

    private String responsibleName;
}
