package hu.bnpi.dhte.inventory.inventoryitem.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidAmountException extends AbstractThrowableProblem {
    public InvalidAmountException(String inventoryId) {
        super(URI.create("inventoryitem/invalid-amount"),
                "Invalid amount",
                Status.BAD_REQUEST,
                String.format("Item with inventory id %s has different amount in the database!", inventoryId));
    }
}
