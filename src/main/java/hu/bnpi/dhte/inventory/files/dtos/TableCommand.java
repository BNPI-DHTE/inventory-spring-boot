package hu.bnpi.dhte.inventory.files.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TableCommand {

    private String inventoryDistrictCode;

    private String inventoryDistrictName;

    private String workingPlace;

    private String responsibleDepartmentName;

    private String responsiblePersonCode;

    private String responsiblePersonName;

    private String inventoryItemID;

    private String inventoryItemName1;

    private String inventoryItemName2;

    private String inventoryItemName3;

    private String dateOfUse;

    private String inventoryItemDescription;

    private String serialNumber;

    private String amount;

    private String notes;
}