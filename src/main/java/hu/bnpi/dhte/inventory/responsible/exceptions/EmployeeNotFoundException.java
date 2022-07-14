package hu.bnpi.dhte.inventory.responsible.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class EmployeeNotFoundException extends AbstractThrowableProblem {
    public EmployeeNotFoundException(String name) {
        super(URI.create("responsible/employee-not-found"),
                "Employee not found",
                Status.NOT_FOUND,
                String.format("Employee not found by name: %s", name));
    }
}
