package hu.bnpi.dhte.inventory.mappers;

import hu.bnpi.dhte.inventory.dtos.CreateInventoryItemCommand;
import hu.bnpi.dhte.inventory.dtos.InventoryItemDto;
import hu.bnpi.dhte.inventory.dtos.UpdateInventoryItemCommand;
import hu.bnpi.dhte.inventory.models.InventoryItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    InventoryItemDto toInventoryItemDto(InventoryItem inventoryItem);

    List<InventoryItemDto> toInventoryItemDto(List<InventoryItem> items);

    CreateInventoryItemCommand toCreateInventoryItemCommand(InventoryItem inventoryItem);

    UpdateInventoryItemCommand toUpdateInventoryItemCommand(InventoryItem inventoryItem);
}
