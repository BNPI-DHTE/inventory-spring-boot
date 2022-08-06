package hu.bnpi.dhte.inventory.inventoryitem.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InventoryItemNotFoundException extends AbstractThrowableProblem {
    public InventoryItemNotFoundException(String inventoryNumber) {
        super(URI.create("inventoryitem/item-not-found"),
                "Inventory item not found",
                Status.NOT_FOUND,
                String.format("Item not found with inventory id: %s", inventoryNumber));
    }
}
