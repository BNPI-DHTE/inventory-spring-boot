package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CorruptResponsibleException extends AbstractThrowableProblem {
    public CorruptResponsibleException(String responsibleId) {
        super(URI.create("responsible/corrupt-responsible"),
                "Corrupt responsible",
                Status.BAD_REQUEST,
                String.format("Responsible with responsible id: %s has another name in the database!", responsibleId));
    }
}
