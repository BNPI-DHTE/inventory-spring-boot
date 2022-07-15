package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ResponsibleCanNotDeleteException extends AbstractThrowableProblem {
    public ResponsibleCanNotDeleteException(long id) {
            super(URI.create("responsible/can-not-delete"),
            "Responsible can't delete",
            Status.NOT_FOUND,
            String.format("Responsible can't delete with id: %d! It has inventory items!", id));
    }
}
