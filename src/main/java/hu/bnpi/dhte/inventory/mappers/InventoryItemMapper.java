package hu.bnpi.dhte.inventory.mappers;

import hu.bnpi.dhte.inventory.dtos.InventoryItemDTO;
import hu.bnpi.dhte.inventory.models.InventoryItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    InventoryItemDTO toInventoryItemDto(InventoryItem inventoryItem);

    List<InventoryItemDTO> toInventoryItemDto(List<InventoryItem> items);
}
