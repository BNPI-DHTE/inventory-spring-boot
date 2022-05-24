package hu.bnpi.dhte.inventory.controllers;

import hu.bnpi.dhte.inventory.dtos.CreateInventoryItemCommand;
import hu.bnpi.dhte.inventory.dtos.InventoryItemDTO;
import hu.bnpi.dhte.inventory.dtos.UpdateInventoryItemCommand;
import hu.bnpi.dhte.inventory.services.InventoryItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/inventoryitems")
public class InventoryItemController {

    private InventoryItemService service;

    public InventoryItemController(InventoryItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<InventoryItemDTO> listInventoryItems(@RequestParam Optional<String> substringOfName) {
        return service.listInventoryItems(substringOfName);
    }

    @GetMapping("/{id}")
    public Optional<InventoryItemDTO> findInventoryItemById(@PathVariable("id") long id) {
        Optional<InventoryItemDTO> item = Optional.empty();
        try {
            item = Optional.of(service.findInventoryItemById(id));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @GetMapping("/findByInventoryId/{inventoryId}")
    public Optional<InventoryItemDTO> findInventoryItemByInventoryId(@PathVariable("inventoryId") String inventoryId) {
        Optional<InventoryItemDTO> item = Optional.empty();
        try {
            item = Optional.of(service.findInventoryItemByInventoryId(inventoryId));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @PostMapping(path = "/save")
    public Optional<InventoryItemDTO> createInventoryItem(@RequestBody CreateInventoryItemCommand command) {
        Optional<InventoryItemDTO> item = Optional.empty();
        try {
            item = Optional.of(service.createInventoryItem(command));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @PostMapping(path = "/saveAll")
    public List<InventoryItemDTO> createMultipleInventoryItem(@RequestBody List<CreateInventoryItemCommand> commands) {
        List<InventoryItemDTO> items = new ArrayList<>();
        try {
            items = commands.stream()
                    .map(command -> service.createInventoryItem(command))
                    .toList();
        } catch (IllegalArgumentException iae){
            log.error(iae.getMessage());
        }
        return items;
    }

    @PutMapping("/update/{id}")
    public Optional<InventoryItemDTO> updateInventoryItem(@PathVariable("id") long id,
                                                          @RequestBody UpdateInventoryItemCommand command) {
        Optional<InventoryItemDTO> item = Optional.empty();
        try {
            return Optional.of(service.updateEmployee(id, command));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInventoryItem(@PathVariable("id") long id) {
        try {
            service.deleteInventoryItem(id);
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
    }
}
