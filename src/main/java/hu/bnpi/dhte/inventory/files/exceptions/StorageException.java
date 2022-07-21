package hu.bnpi.dhte.inventory.files.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class StorageException extends AbstractThrowableProblem {

    public StorageException(String message) {
        super(URI.create("files/cannot-store"),
                "Cannot store file",
                Status.BAD_REQUEST,
                message);
    }

    public StorageException(String message, Throwable cause) {
        super(URI.create("files/cannot-store"),
                "Cannot store file",
                Status.BAD_REQUEST,
                message + " " + cause.getCause());
    }
}
