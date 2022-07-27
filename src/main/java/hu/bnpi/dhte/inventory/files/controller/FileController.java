package hu.bnpi.dhte.inventory.files.controller;

import hu.bnpi.dhte.inventory.files.service.FileService;
import hu.bnpi.dhte.inventory.inventoryitem.dtos.InventoryItemShortDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/files")
public class FileController {

    private FileService service;

    @PostMapping(value = "/excel", consumes = {MULTIPART_FORM_DATA_VALUE})
    public List<InventoryItemShortDetails> readExcel(@RequestParam(value = "file") MultipartFile file) {
        return service.readExcel(file);
    }
}
