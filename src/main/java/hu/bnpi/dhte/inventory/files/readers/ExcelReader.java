package hu.bnpi.dhte.inventory.files.readers;

import hu.bnpi.dhte.inventory.files.exceptions.CannotReadFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ExcelReader implements FileReader {

    /**
     * Reads Excel XML (.xlsx) file's first sheet, and creates a list of inventory items.
     * The header must be the first line of the sheet.
     * Column name is irrelevant, but order must be as follows:
     * Inventory District Code, Inventory District Name, Working Place, Responsible Department Code, Responsible Department Name, Responsible Person Code,
     * Responsible Person Name, Inventory Item ID, Inventory Item Name 1, Inventory Item Name 2, Inventory Item Name 3, Date Of Use, Inventory Item Description,
     * Serial Number, Amount, Notes
     */

    @Override
    public List<Map<String, String>> readTable(Path path) {
        List<Map<String, String>> items = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(path.toString())) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                if (rowIterator.next().getRowNum() == 0) {
                    rowIterator.next();
                } else {
                    Map<String, String> item = new HashMap<>();
                    XSSFRow row = (XSSFRow) rowIterator.next();
                    for (int i = 0; i < keys.size(); i++) {
                        String value = row.getCell(i).toString();
                        item.put(keys.get(i), value);
                    }
                    items.add(item);
                }
            }
            return items;
        } catch (IOException ioe) {
            throw new CannotReadFileException(path.toString(), ioe);
        }
    }
}
