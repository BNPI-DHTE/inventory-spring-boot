package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class DepartmentNotFoundException extends AbstractThrowableProblem {
    public DepartmentNotFoundException(long id) {
        super(URI.create("responsible/department-not-found"),
                "Department not found",
                Status.NOT_FOUND,
                String.format("Department not found with id: %d", id));
    }
}
