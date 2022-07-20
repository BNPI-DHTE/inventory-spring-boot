package hu.bnpi.dhte.inventory.files.readers;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface FileReader {

    List<String> keys = List.of("inventoryDistrictCode", "inventoryDistrictName", "workingPlace", "responsibleDepartmentCode",
            "responsibleDepartmentName", "responsiblePersonCode", "responsiblePersonName", "inventoryItemID", "inventoryItemName1", "inventoryItemName2",
            "inventoryItemName3", "dateOfUse", "inventoryItemDescription", "serialNumber", "amount", "notes");

    List<Map<String,String>> readTable(Path path);
}
