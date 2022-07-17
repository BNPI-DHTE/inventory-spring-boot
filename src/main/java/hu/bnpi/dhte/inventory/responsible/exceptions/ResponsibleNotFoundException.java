package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ResponsibleNotFoundException extends AbstractThrowableProblem {
    public ResponsibleNotFoundException(long id) {
        super(URI.create("responsible/not-found"),
                "Responsible not found",
                Status.NOT_FOUND,
                String.format("Responsible not found with id: %d", id));
    }
}
