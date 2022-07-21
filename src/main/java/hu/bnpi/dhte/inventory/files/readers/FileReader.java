package hu.bnpi.dhte.inventory.files.readers;

import hu.bnpi.dhte.inventory.files.dtos.TableCommand;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileReader {

    List<TableCommand> readTable(MultipartFile file);
}
