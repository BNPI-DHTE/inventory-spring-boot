package hu.bnpi.dhte.inventory.controllers;

import hu.bnpi.dhte.inventory.dtos.CreateInventoryItemCommand;
import hu.bnpi.dhte.inventory.dtos.InventoryItemDto;
import hu.bnpi.dhte.inventory.dtos.UpdateInventoryItemCommand;
import hu.bnpi.dhte.inventory.services.InventoryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/inventoryitems")
@Tag(name = "Operations on inventory items")
public class InventoryItemController {

    private InventoryItemService service;

    public InventoryItemController(InventoryItemService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(description = "List all inventory items in the database, or filtered list by substring of name")
    public List<InventoryItemDto> listInventoryItems(@RequestParam Optional<String> substringOfName) {
        return service.listInventoryItems(substringOfName);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find an inventory item by database id")
    public Optional<InventoryItemDto> findInventoryItemById(@PathVariable("id") long id) {
        Optional<InventoryItemDto> item = Optional.empty();
        try {
            item = Optional.of(service.findInventoryItemById(id));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @GetMapping("/findByInventoryId/{inventoryId}")
    @Operation(description = "Find an inventory item by inventory id")
    public Optional<InventoryItemDto> findInventoryItemByInventoryId(@PathVariable("inventoryId") String inventoryId) {
        Optional<InventoryItemDto> item = Optional.empty();
        try {
            item = Optional.of(service.findInventoryItemByInventoryId(inventoryId));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @PostMapping(path = "/save")
    @Operation(description = "Save an item into database")
    public Optional<InventoryItemDto> createInventoryItem(@RequestBody CreateInventoryItemCommand command) {
        Optional<InventoryItemDto> item = Optional.empty();
        try {
            item = Optional.of(service.createInventoryItem(command));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @PostMapping(path = "/saveAll")
    @Operation(description = "Save an multiple items into database")
    public List<InventoryItemDto> createMultipleInventoryItem(@RequestBody List<CreateInventoryItemCommand> commands) {
        List<InventoryItemDto> items = new ArrayList<>();
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
    @Operation(description = "Update an item by database id")
    public Optional<InventoryItemDto> updateInventoryItem(@PathVariable("id") long id,
                                                          @RequestBody UpdateInventoryItemCommand command) {
        Optional<InventoryItemDto> item = Optional.empty();
        try {
            return Optional.of(service.updateInventoryItem(id, command));
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        return item;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete an item by database id")
    public void deleteInventoryItem(@PathVariable("id") long id) {
        try {
            service.deleteInventoryItem(id);
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
    }
}
