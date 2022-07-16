package hu.bnpi.dhte.inventory.inventoryitem.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class KitNotFoundException extends AbstractThrowableProblem {
    public KitNotFoundException(long kitId) {
        super(URI.create("inventoryitem/kit-not-found"),
                "Kit not found",
                Status.BAD_REQUEST,
                String.format("Kit not found with id: %d", kitId));
    }
}
