package hu.bnpi.dhte.inventory.inventoryitem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KitDetails {

    private Long kitId;

    private String name;

    private List<InventoryItemDetails> items;
}
