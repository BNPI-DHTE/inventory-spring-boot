package hu.bnpi.dhte.inventory.responsible.dtos;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDto {

    private Long id;

    private String name;

    private String leaderName;

    private List<InventoryItem> items;
}
