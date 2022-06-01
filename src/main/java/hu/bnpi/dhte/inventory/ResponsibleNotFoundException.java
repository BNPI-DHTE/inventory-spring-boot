package hu.bnpi.dhte.inventory;

public class ResponsibleNotFoundException extends RuntimeException {
    public ResponsibleNotFoundException(long id) {
        super("Cannot find responsible with id: " + id);
    }
}
