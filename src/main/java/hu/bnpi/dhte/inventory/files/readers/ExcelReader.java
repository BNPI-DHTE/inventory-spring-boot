package hu.bnpi.dhte.inventory.files.readers;

import hu.bnpi.dhte.inventory.files.dtos.TableCommand;
import hu.bnpi.dhte.inventory.files.exceptions.CannotReadFileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ExcelReader implements FileReader {

    /**
     * Reads Excel XML (.xlsx) file's first sheet, and creates a list of inventory items.
     * The header must be the first line of the sheet.
     * Column name is irrelevant, but order must be as follows:
     * Inventory District Code, Inventory District Name, Working Place, Responsible Department Code, Responsible Department Name, Responsible Person Code,
     * Responsible Person Name, Inventory Item ID, Inventory Item Name 1, Inventory Item Name 2, Inventory Item Name 3, Date Of Use, Inventory Item Description,
     * Serial Number, Amount, Notes
     */

    //TODO Refactor this huge readTable method! Maybe TableCommand could be separate into two entities.
    @Override
    public List<TableCommand> readTable(MultipartFile file) {
        List<TableCommand> items = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                    XSSFRow row = (XSSFRow) rowIterator.next();
                    if (row.getRowNum() == 0) {
                        row = (XSSFRow) rowIterator.next();
                    }
                    log.info("Sorszám: " + row.getRowNum());
                    List<String> cellValues = new ArrayList<>();
                    for (int cellNumber = 0; cellNumber < 15; cellNumber++) {
                        XSSFCell cell = row.getCell(cellNumber);
                        if (cell == null) {
                            cellValues.add("");
                        } else {
                            switch (cellNumber) {
                                case 0,1,2,3,4,5,7,8,9,11,12,14 -> cellValues.add(cell.getStringCellValue());
                                case 6,13 -> cellValues.add(Long.toString(Math.round(cell.getNumericCellValue())));
                                case 10 -> cellValues.add(cell.getLocalDateTimeCellValue().toLocalDate().toString());
                                default -> cellValues.add("");
                            }
                        }
                    }
                    TableCommand item = new TableCommand(cellValues.get(0),
                            cellValues.get(1),
                            cellValues.get(2),
                            cellValues.get(3),
                            cellValues.get(4),
                            cellValues.get(5),
                            cellValues.get(6),
                            cellValues.get(7),
                            cellValues.get(8),
                            cellValues.get(9),
                            cellValues.get(10),
                            cellValues.get(11),
                            cellValues.get(12),
                            cellValues.get(13),
                            cellValues.get(14));
                    log.info(item.toString());
                    items.add(item);
            }
            return items;
        } catch (IOException ioe) {
            throw new CannotReadFileException(file.getOriginalFilename(), ioe);
        }
    }
}
