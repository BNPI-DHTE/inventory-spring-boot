package hu.bnpi.dhte.inventory.services;

import hu.bnpi.dhte.inventory.dtos.CreateInventoryItemCommand;
import hu.bnpi.dhte.inventory.dtos.InventoryItemDto;
import hu.bnpi.dhte.inventory.dtos.UpdateInventoryItemCommand;
import hu.bnpi.dhte.inventory.mappers.InventoryItemMapper;
import hu.bnpi.dhte.inventory.models.InventoryItem;
import hu.bnpi.dhte.inventory.models.ItemType;
import hu.bnpi.dhte.inventory.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    private InventoryItemRepository repository;

    private InventoryItemMapper mapper;

    public InventoryItemService(InventoryItemRepository repository, InventoryItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InventoryItemDto findInventoryItemById(Long id) {
        return mapper.toInventoryItemDto(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find item with id: " + id)));
    }

    public List<InventoryItemDto> listInventoryItems(Optional<String> substringOfName) {
        return mapper.toInventoryItemDto(repository.findAll()).stream()
                .filter(item -> substringOfName.isEmpty() || item.getName().toLowerCase().contains(substringOfName.get().toLowerCase()))
                .toList();
    }

    public InventoryItemDto createInventoryItem(CreateInventoryItemCommand command) {
        InventoryItem item = new InventoryItem(command.getInventoryId(),
                ItemType.valueOf(command.getItemType()),
                command.getName(),
                command.getAmount());
        if (isValidString(command.getDescription())) {
            item.setDescription(command.getDescription());
        }
        if (isValidString(command.getSerialNumber())) {
            item.setSerialNumber(command.getSerialNumber());
        }
        if (command.getResponsible() != null) {
            item.setResponsible(command.getResponsible());
        }
        item = repository.save(item);
        return mapper.toInventoryItemDto(item);
    }

    private boolean isValidString(String text) {
         return text != null && !text.isBlank();
    }

    public InventoryItemDto updateInventoryItem(long id, UpdateInventoryItemCommand command) {
        InventoryItem item = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find item with id: " + id));
        if (isValidString(command.getName())) {
            item.setName(command.getName());
        }
        if (isValidString(command.getDescription())) {
            item.setDescription(command.getDescription());
        }
        if (isValidString(command.getSerialNumber())) {
            item.setSerialNumber(command.getSerialNumber());
        }
        if (command.getItemType() != null) {
            item.setItemType(ItemType.valueOf(command.getItemType()));
        }
        if (command.getAmount() > 0) {
            item.setAmount(command.getAmount());
        }
        if (command.getResponsible() != null) {
            item.setResponsible(command.getResponsible());
        }
        return mapper.toInventoryItemDto(repository.save(item));
    }

    public void deleteInventoryItem(long id) {
        repository.deleteById(id);
    }

    public InventoryItemDto findInventoryItemByInventoryId(String inventoryId) {
        return mapper.toInventoryItemDto(repository.findAll().stream()
                .filter(item -> item.getInventoryId().equals(inventoryId))
                .findFirst().orElseThrow(()-> new IllegalArgumentException("Cannot find item with inventory id: " + inventoryId)));
    }
}
