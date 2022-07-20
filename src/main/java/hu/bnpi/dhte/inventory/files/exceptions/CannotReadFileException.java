package hu.bnpi.dhte.inventory.files.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.IOException;
import java.net.URI;

public class CannotReadFileException extends AbstractThrowableProblem {
    public CannotReadFileException(String filename, IOException ioe) {
        super(URI.create("files/cannot-read"),
                "File cannot read",
                Status.BAD_REQUEST,
                String.format("File: %s cannot read! %s", filename, ioe.getCause()));
    }
}
