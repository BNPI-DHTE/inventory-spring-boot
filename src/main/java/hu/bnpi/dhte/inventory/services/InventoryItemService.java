package hu.bnpi.dhte.inventory.services;

import hu.bnpi.dhte.inventory.dtos.CreateInventoryItemCommand;
import hu.bnpi.dhte.inventory.dtos.InventoryItemDTO;
import hu.bnpi.dhte.inventory.dtos.UpdateInventoryItemCommand;
import hu.bnpi.dhte.inventory.mappers.InventoryItemMapper;
import hu.bnpi.dhte.inventory.models.InventoryItem;
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

    public InventoryItemDTO findInventoryItemById(Long id) {
        return mapper.toInventoryItemDto(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find item with id: " + id)));
    }

    public List<InventoryItemDTO> listInventoryItems(Optional<String> substringOfName) {
        List<InventoryItemDTO> items = mapper.toInventoryItemDto(repository.findAll());
        return items.stream()
                .filter(item -> substringOfName.isEmpty() || item.getName().toLowerCase().contains(substringOfName.get().toLowerCase()))
                .toList();
    }

    public InventoryItemDTO createInventoryItem(CreateInventoryItemCommand command) {
        InventoryItem item = new InventoryItem(command.getInventoryId(), command.getItemType(), command.getName());
        if (isValidString(command.getDescription())) {
            item.setDescription(command.getDescription());
        }
        if (isValidString(command.getSerialNumber())) {
            item.setSerialNumber(command.getSerialNumber());
        }
        if (command.getAmount() > 0) {
            item.setAmount(command.getAmount());
        }
        item = repository.save(item);
        return mapper.toInventoryItemDto(item);
    }

    private boolean isValidString(String text) {
         return text != null && !text.isBlank();
    }

    public InventoryItemDTO updateEmployee(long id, UpdateInventoryItemCommand command) {
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
            item.setItemType(command.getItemType());
        }
        if (command.getAmount() > 0) {
            item.setAmount(command.getAmount());
        }
        return mapper.toInventoryItemDto(repository.save(item));
    }

    public void deleteInventoryItem(long id) {
        repository.deleteById(id);
    }
}
