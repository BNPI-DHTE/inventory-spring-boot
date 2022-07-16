package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ResponsibleCanNotDeleteException extends AbstractThrowableProblem {
    public ResponsibleCanNotDeleteException(long id) {
            super(URI.create("responsible/can-not-delete"),
            "Responsible can't delete",
            Status.BAD_REQUEST,
            String.format("There are inventory items in responsibility of responsible with id: %d!", id));
    }

    public ResponsibleCanNotDeleteException(String nameOfLeader) {
        super(URI.create("responsible/can-not-delete"),
                "Responsible can't delete",
                Status.BAD_REQUEST,
                String.format("There is a department under leadership of %s", nameOfLeader));
    }
}
