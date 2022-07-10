package hu.bnpi.dhte.inventory.inventoryitem.model;

import java.time.LocalDate;

public interface Amortization {

    double amortize(double startUnitPrice, LocalDate acquisitionDate, LocalDate actualDate);
}
