package hu.bnpi.dhte.inventory.inventoryitem.mapper;

import hu.bnpi.dhte.inventory.inventoryitem.dtos.InventoryItemDetails;
import hu.bnpi.dhte.inventory.inventoryitem.dtos.InventoryItemShortDetails;
import hu.bnpi.dhte.inventory.inventoryitem.dtos.KitDetails;
import hu.bnpi.dhte.inventory.inventoryitem.dtos.KitSummary;
import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import hu.bnpi.dhte.inventory.inventoryitem.model.Kit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    InventoryItemDetails toInventoryItemDetails(InventoryItem item);

    @Mapping(target = "responsibleName", source = "responsible.name")
    InventoryItemShortDetails toInventoryItemShortDetails(InventoryItem item);

    @Mapping(target = "kitId", source = "id")
    KitSummary toKitSummary(Kit kit);

    @Mapping(target = "kitId", source = "id")
    KitDetails toKitDetails(Kit kit);
}
