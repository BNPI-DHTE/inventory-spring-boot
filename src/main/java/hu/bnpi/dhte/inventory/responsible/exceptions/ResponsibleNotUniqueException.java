package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ResponsibleNotUniqueException extends AbstractThrowableProblem {
    public ResponsibleNotUniqueException(String responsiblePersonCode, String workingPlace) {
        super(URI.create("responsible/not-unique"),
                "Responsible not unique",
                Status.BAD_REQUEST,
                String.format("Unable to decide responsible by responsible IDs: employee ID: %s, department ID %s", responsiblePersonCode, workingPlace));
    }
}
