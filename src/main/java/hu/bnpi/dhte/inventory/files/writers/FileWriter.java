package hu.bnpi.dhte.inventory.files.writers;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;

import java.nio.file.Path;
import java.util.List;

public interface FileWriter {

    Path writeTable(List<InventoryItem> items);
}
