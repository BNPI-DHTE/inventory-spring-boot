package hu.bnpi.dhte.inventory.files.readers;

import hu.bnpi.dhte.inventory.files.dtos.TableCommand;
import hu.bnpi.dhte.inventory.files.exceptions.CannotReadFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public List<TableCommand> readTable(MultipartFile file) {
        List<TableCommand> items = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                if (rowIterator.next().getRowNum() == 0) {
                    rowIterator.next();
                } else {
                    XSSFRow row = (XSSFRow) rowIterator.next();
                    TableCommand item = new TableCommand(
                            row.getCell(0).toString(),
                            row.getCell(1).toString(),
                            row.getCell(2).toString(),
                            row.getCell(3).toString(),
                            row.getCell(4).toString(),
                            row.getCell(5).toString(),
                            row.getCell(6).toString(),
                            row.getCell(7).toString(),
                            row.getCell(8).toString(),
                            row.getCell(9).toString(),
                            row.getCell(10).toString(),
                            row.getCell(11).toString(),
                            row.getCell(12).toString(),
                            row.getCell(13).toString(),
                            row.getCell(14).toString());
                    items.add(item);
                }
            }
            return items;
        } catch (IOException ioe) {
            throw new CannotReadFileException(file.getOriginalFilename(), ioe);
        }
    }
}
