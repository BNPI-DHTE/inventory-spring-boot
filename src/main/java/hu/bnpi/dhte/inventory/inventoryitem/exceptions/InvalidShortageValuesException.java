package hu.bnpi.dhte.inventory.inventoryitem.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidShortageValuesException extends AbstractThrowableProblem {

    public InvalidShortageValuesException() {
        super(URI.create("inventoryitem/invalid-shortage-values"),
                "Invalid shortage values:",
                Status.BAD_REQUEST,
                "Item cannot be disposal and deficit at the same time.");
    }
}
