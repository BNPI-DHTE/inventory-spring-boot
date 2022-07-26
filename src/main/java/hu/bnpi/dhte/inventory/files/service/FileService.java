package hu.bnpi.dhte.inventory.files.service;

import hu.bnpi.dhte.inventory.files.dtos.TableCommand;
import hu.bnpi.dhte.inventory.files.readers.ExcelReader;
import hu.bnpi.dhte.inventory.inventoryitem.dtos.InventoryItemDetails;
import hu.bnpi.dhte.inventory.inventoryitem.mapper.InventoryItemMapper;
import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import hu.bnpi.dhte.inventory.inventoryitem.repositories.InventoryItemRepository;
import hu.bnpi.dhte.inventory.responsible.exceptions.CorruptResponsibleException;
import hu.bnpi.dhte.inventory.responsible.exceptions.ResponsibleNotUniqueException;
import hu.bnpi.dhte.inventory.responsible.mapper.ResponsibleMapper;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import hu.bnpi.dhte.inventory.responsible.repositories.ResponsibleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class FileService {

    private ExcelReader excelReader;

    private ResponsibleRepository responsibleRepository;

    private InventoryItemRepository inventoryItemRepository;

    private ResponsibleMapper responsibleMapper;

    private InventoryItemMapper inventoryItemMapper;

    //TODO New conception: check, if inventoryId (responsible or item) is in the database.
    //  If it's true, check, if it is the same item (by name (responsible), or by serial number, dateOfUse, additional fields(item))
    //      If it's true, check if amount was changed in item.
    //          If it's true, we need to return message with the item details.
    //          If it's false, nothing happens.
    //      If it's false, we need to return message with the item details.
    // If it's false, save responsible, and save item and add to the responsible.
    // Possibly we can send back a json or another file, as the original one, but extended with a message, what happened with the
    // responsible and with the item.

    public List<InventoryItemDetails> readExcel(MultipartFile file) {
        List<InventoryItemDetails> itemDetails = new ArrayList<>();
        List<TableCommand> items = excelReader.readTable(file);
        for (TableCommand command : items) {
            Responsible responsible = getResponsible(command);
            InventoryItem item = getInventoryItem(responsible, command);
            responsibleRepository.save(responsible);
            log.info("Responsible saved: " + responsible.getResponsibleId() + ", " + responsible.getName());
            inventoryItemRepository.save(item);
            itemDetails.add(inventoryItemMapper.toInventoryItemDetails(item));
        }
        return itemDetails;
    }

    private InventoryItem getInventoryItem(Responsible responsible, TableCommand command) {
        InventoryItem item;
        Optional<InventoryItem> optionalItem = inventoryItemRepository.findByInventoryId(command.getInventoryItemID());
        if (optionalItem.isPresent()) {
            //TODO: validateInventoryItem(command, item.get()); How to validate, as the name will be replaced in some cases. Check amount!
            // Check if responsible is the same as in the database!
            item = optionalItem.get();
        } else {
            item = new InventoryItem(command.getInventoryItemID(),
                    command.getInventoryItemName1(),
                    LocalDate.parse(command.getDateOfUse()),
                    command.getInventoryItemDescription(),
                    command.getSerialNumber(),
                    "[\n{\n" + "\"inventoryItemName2\":\"" + command.getInventoryItemName2() +
                            "\"\n\"inventoryItemName3\":\"" + command.getInventoryItemName3() +
                            "\"\n}\n]",
                    (int) Math.round(Double.parseDouble(command.getAmount())));
            responsible.addInventoryItem(item);
            inventoryItemRepository.save(item);
        }
        return item;
    }

    private Responsible getResponsible(TableCommand command) {
        Responsible responsible;
        List<Responsible> listOfPossibleResponsible = new ArrayList<>();
        listOfPossibleResponsible.addAll(responsibleRepository.findAllByResponsibleId(command.getResponsiblePersonCode()));
        listOfPossibleResponsible.addAll(responsibleRepository.findAllByResponsibleId(command.getWorkingPlace()));
        log.info("Size of possible responsible is: " + listOfPossibleResponsible.size());
        if (listOfPossibleResponsible.isEmpty()) {
            responsible = createResponsible(command);
            log.info("Responsible created with name: " + responsible.getName());
        } else if (listOfPossibleResponsible.size() > 1) {
            throw new ResponsibleNotUniqueException(command.getResponsiblePersonCode(), command.getWorkingPlace());
        } else {
            validateResponsible(command, listOfPossibleResponsible.get(0));
            responsible = listOfPossibleResponsible.get(0);
        }
        return responsible;
    }

    private void validateResponsible(TableCommand command, Responsible responsible) {
        if (!responsible.getName().equals(command.getResponsiblePersonName()) && !responsible.getName().equals(command.getResponsibleDepartmentName())) {
            throw new CorruptResponsibleException(responsible.getResponsibleId());
        }
    }

    private Responsible createResponsible(TableCommand command) {
        Responsible responsible;
        if (command.getResponsiblePersonCode().isBlank()) {
            responsible = createDepartment(command);
        } else {
            responsible = createEmployee(command);
        }
        responsibleRepository.save(responsible);
        return responsible;
    }

    private Responsible createDepartment(TableCommand command) {
        return new Department(command.getWorkingPlace(), command.getResponsibleDepartmentName());
    }

    private Responsible createEmployee(TableCommand command) {
        return new Employee(command.getResponsiblePersonCode(), command.getResponsiblePersonName());
    }
}
