package hu.bnpi.dhte.inventory.inventoryitem.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidResponsibleException extends AbstractThrowableProblem {
    public InvalidResponsibleException(String inventoryNumber) {
        super(URI.create("inventoryitem/invalid-responsible-values"),
                "Invalid responsible",
                Status.BAD_REQUEST,
                String.format("Item with inventory id %s has different responsible in the database!", inventoryNumber));
    }
}
