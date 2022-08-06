package hu.bnpi.dhte.inventory.inventoryitem.service;

import hu.bnpi.dhte.inventory.inventoryitem.dtos.*;
import hu.bnpi.dhte.inventory.inventoryitem.exceptions.InvalidShortageValuesException;
import hu.bnpi.dhte.inventory.inventoryitem.exceptions.InventoryItemNotFoundException;
import hu.bnpi.dhte.inventory.inventoryitem.exceptions.KitNotFoundException;
import hu.bnpi.dhte.inventory.inventoryitem.mapper.InventoryItemMapper;
import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import hu.bnpi.dhte.inventory.inventoryitem.model.Kit;
import hu.bnpi.dhte.inventory.inventoryitem.repositories.InventoryItemRepository;
import hu.bnpi.dhte.inventory.inventoryitem.repositories.KitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class InventoryItemService {

    private InventoryItemRepository inventoryItemRepository;

    private KitRepository kitRepository;

    private InventoryItemMapper mapper;

    public List<InventoryItemDetails> findAllItems() {
        return inventoryItemRepository.findAll().stream()
                .map(inventoryItem -> mapper.toInventoryItemDetails(inventoryItem))
                .toList();
    }

    public InventoryItemDetails findByInventoryNumber(String inventoryNumber) {
        return mapper.toInventoryItemDetails(inventoryItemRepository.findByInventoryNumber(inventoryNumber)
                .orElseThrow(() -> new InventoryItemNotFoundException(inventoryNumber)));
    }

    public List<KitDetails> findAllKits() {
        return kitRepository.findAll().stream()
                .map(kit -> mapper.toKitDetails(kit))
                .toList();
    }

    public InventoryItemDetails saveInventoryItem(CreateInventoryItemCommand command) {
        InventoryItem item = new InventoryItem(command.getInventoryNumber(),
                command.getItemType(),
                command.getName(),
                command.getDateOfUse(),
                command.getDescription(),
                command.getSerialNumber(),
                command.getCategory(),
                command.getAdditionalFields(),
                command.getLocation(),
                command.isToDisposal(),
                command.isDeficit(),
                command.getInitialPrice(),
                command.getAmount());
        inventoryItemRepository.save(item);
        return mapper.toInventoryItemDetails(item);
    }

    public KitDetails saveKit(CreateKitCommand command) {
        Kit kit = new Kit(command.getName());
        kitRepository.save(kit);
        return mapper.toKitDetails(kit);
    }

    //TODO Refactor or simplify this huge method!

    public InventoryItemDetails updateInventoryItem(String inventoryNumber, UpdateInventoryItemCommand command) {
        InventoryItem item = getInventoryItemByInventoryNumber(inventoryNumber);
        if (command.getName() != null && !command.getName().isBlank() && !command.getName().equals(item.getName())) {
            item.setName(command.getName());
        }
        if (command.getDescription() != null && !command.getDescription().isBlank() && !command.getDescription().equals(item.getDescription())) {
            item.setDescription(command.getDescription());
        }
        if (command.getSerialNumber() != null && !command.getSerialNumber().isBlank() && !command.getSerialNumber().equals(item.getSerialNumber())) {
            item.setSerialNumber(command.getSerialNumber());
        }
        if (command.getCategory() != null && !command.getCategory().isBlank() && !command.getCategory().equals(item.getCategory())) {
            item.setCategory(command.getCategory());
        }
        if (command.getAdditionalFields() != null && !command.getAdditionalFields().isBlank() && !command.getAdditionalFields().equals(item.getAdditionalFields())) {
            item.setAdditionalFields(command.getAdditionalFields());
        }
        if (command.getLocation() != null && !command.getLocation().isBlank() && !command.getLocation().equals(item.getLocation())) {
            item.setLocation(command.getLocation());
        }
        if (command.getInitialPrice() != item.getInitialPrice()) {
            item.setInitialPrice(command.getInitialPrice());
        }
        if (command.getAmount() != item.getAmount()) {
            item.setAmount(command.getAmount());
        }
        return mapper.toInventoryItemDetails(item);
    }

    public InventoryItemDetails setShortage(String inventoryNumber, Optional<Boolean> isDisposal, Optional<Boolean> isDeficit) {
        validateShortageValues(inventoryNumber, isDisposal, isDeficit);
        InventoryItem item = getInventoryItemByInventoryNumber(inventoryNumber);
        isDisposal.ifPresent(item::setToDisposal);
        isDeficit.ifPresent(item::setDeficit);
        return mapper.toInventoryItemDetails(item);
    }

    public void removeKit(long kitId) {
        Kit kit = kitRepository.findById(kitId)
                .orElseThrow(() -> new KitNotFoundException(kitId));
        kit.getItems().forEach(InventoryItem::removeKit);
        kitRepository.delete(kit);
    }

    private void validateShortageValues(String inventoryNumber, Optional<Boolean> isDisposal, Optional<Boolean> isDeficit) {
        if (isDisposal.isPresent() && isDeficit.isPresent() || isDisposal.isEmpty() && isDeficit.isEmpty()) {
            throw new InvalidShortageValuesException();
        }
        InventoryItem item = getInventoryItemByInventoryNumber(inventoryNumber);
        if (isDisposal.isPresent() && isDisposal.get().equals(item.isDeficit()) || isDeficit.isPresent() && isDeficit.get().equals(item.isToDisposal())) {
            throw new InvalidShortageValuesException();
        }
    }

    private InventoryItem getInventoryItemByInventoryNumber(String inventoryNumber) {
        return inventoryItemRepository.findByInventoryNumber(inventoryNumber)
                .orElseThrow(() -> new InventoryItemNotFoundException(inventoryNumber));
    }

    public List<InventoryItemDetails> findAllByResponsibleNumber(String responsibleNumber) {
        return inventoryItemRepository.findAllByResponsibleResponsibleNumber(responsibleNumber).stream()
                .map(item -> mapper.toInventoryItemDetails(item))
                .toList();
    }

    public List<InventoryItemDetails> findAllByResponsibleName(String nameSubstring) {
        return inventoryItemRepository.findAllByResponsibleName(nameSubstring).stream()
                .map(item -> mapper.toInventoryItemDetails(item))
                .toList();
    }
}
