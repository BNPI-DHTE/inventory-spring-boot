package hu.bnpi.dhte.inventory.inventoryitem.controller;

import hu.bnpi.dhte.inventory.inventoryitem.dtos.*;
import hu.bnpi.dhte.inventory.inventoryitem.service.InventoryItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/items")
public class InventoryItemController {

    private InventoryItemService service;

    @GetMapping(value = "/all")
    public List<InventoryItemDetails> findAllItems() {
        return service.findAllItems();
    }

    @GetMapping(value = "/{itemId}")
    public InventoryItemDetails findByInventoryId(@PathVariable String itemId) {
        return service.findByInventoryId(itemId);
    }

    @GetMapping(value = "/kits")
    public List<KitDetails> findAllKits() {
        return service.findAllKits();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryItemDetails saveInventoryItem(@Valid @RequestBody CreateInventoryItemCommand command) {
        return service.saveInventoryItem(command);
    }

    @PostMapping("/kits")
    @ResponseStatus(HttpStatus.CREATED)
    public KitDetails saveKit(@Valid @RequestBody CreateKitCommand command) {
        return service.saveKit(command);
    }

    @PutMapping(value = "/{inventoryId}")
    public InventoryItemDetails updateInventoryItem(@PathVariable("inventoryId") String inventoryId, @Valid @RequestBody UpdateInventoryItemCommand command) {
        return service.updateInventoryItem(inventoryId, command);
    }

    @PutMapping(value = "/{inventoryId}/shortage")
    public InventoryItemDetails setShortage(@PathVariable("inventoryId") String inventoryId, @RequestParam Optional<Boolean> isDisposal, @RequestParam Optional<Boolean> isDeficit) {
        return service.setShortage(inventoryId, isDisposal, isDeficit);
    }

    @DeleteMapping(value = "/kits/{kitId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeKit(@PathVariable("kitId") long kitId) {
        service.removeKit(kitId);
    }
}
