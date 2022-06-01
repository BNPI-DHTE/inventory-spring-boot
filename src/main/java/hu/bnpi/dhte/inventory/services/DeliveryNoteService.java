package hu.bnpi.dhte.inventory.services;

import hu.bnpi.dhte.inventory.ResponsibleNotFoundException;
import hu.bnpi.dhte.inventory.models.DeliveryNote;
import hu.bnpi.dhte.inventory.models.InventoryItem;
import hu.bnpi.dhte.inventory.repositories.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryNoteService {

    private ResponsibleRepository repository;

    public void updateInventoryItemsByDeliveryNote(DeliveryNote deliveryNote) {
        deliveryNote.getItems().forEach(item -> updateResponsible(item, deliveryNote));
    }

    private InventoryItem updateResponsible(InventoryItem inventoryItem, DeliveryNote deliveryNote) {
        inventoryItem.setResponsible(repository.findById(deliveryNote.getNewResponsibleId())
                .orElseThrow(() -> new ResponsibleNotFoundException(deliveryNote.getNewResponsibleId())));
        return inventoryItem;
    }
}
