package hu.bnpi.dhte.inventory.event.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class EventNotFoundException extends AbstractThrowableProblem {
    public EventNotFoundException(String noteNumber) {
        super(URI.create("events/event-not-found"),
                "Event not found",
                Status.NOT_FOUND,
                String.format("Event not found with note number: %s", noteNumber));
    }
}
