let items =
    `[
        {
            "id": 3,
            "inventoryId": "DFRL785496",
            "itemType": "INVESTMENT",
            "name": "Tree planting on Átány 057/98",
            "amount": 1,
            "responsibleType": "DEPARTMENT",
            "responsibleId": 5
        },
        {
            "id": 0,
            "inventoryId": "AB1548",
            "itemType": "HIGH_VALUE_ASSET",
            "name": "laptop computer",
            "description": "Lenovo Legion 5",
            "serialNumber": "154896AB-456870C",
            "amount": 1,
            "responsibleType": "EMPLOYEE",
            "responsibleId": 0
        },
        {
            "id": 1,
            "inventoryId": "AB16448",
            "itemType": "REAL_ESTATE",
            "name": "Dormánd 098/45",
            "description": "szántó",
            "amount": 1,
            "responsibleType": "DEPARTMENT",
            "responsibleId": 3
        },
        {
            "id": 2,
            "inventoryId": "AB1DC4587",
            "itemType": "HIGH_VALUE_ASSET",
            "name": "binocular",
            "description": "Kowa Genesis",
            "serialNumber": "487502",
            "amount": 1,
            "responsibleType": "EMPLOYEE",
            "responsibleId": 0
        },
        {
            "id": 4,
            "inventoryId": "KJD4587JT",
            "itemType": "CONSUMABLE",
            "name": "plastic cup",
            "amount": 1000,
            "responsibleType": "DEPARTMENT",
            "responsibleId": 8
        }
    ]`;

function createAnyElement(name, attributes) {
    let element = document.createElement(name);
    for (let k in attributes) {
        element.setAttribute(k, attributes[k]);
    }
    return element;
}

function createHtmlTable(rowDivId, tableId, data) {
    createTableElement(rowDivId, tableId);
    createHtmlTableHeader(tableId, data);
    createTableBody(tableId, data);
}

function createTableElement(rowDivId, tableId) {
    let colDiv = createColumnDiv(rowDivId);
    let table = document.getElementById(tableId);
    if (!table) {
        table = createAnyElement("table",
            {
                id: tableId,
                class: "display table table-dark table-striped table-bordered table-hover table-sm",
                width: "100%"
            });
        colDiv.appendChild(table);
    } else {
        table.innerHTML = "";
    }
}

function createColumnDiv(rowDivId) {
    let rowDiv = document.getElementById(rowDivId);
    let colDiv = createAnyElement("col", {
        class: "col-xl"
    });
    rowDiv.appendChild(colDiv);
    return colDiv;
}

function createHtmlTableHeader(tableId, data) {
    let table = document.getElementById(tableId);
    let tableHeader = createAnyElement("thead");
    table.appendChild(tableHeader);
    let columnNames = getColumnNames(data);
    let tableHeaderRow = createAnyElement("tr");
    for (let i = 0; i < columnNames.length; i++) {
        let tableHeaderCell = createAnyElement("th");
        tableHeaderCell.innerHTML = columnNames[i];
        tableHeaderRow.appendChild(tableHeaderCell);
    }
    tableHeader.appendChild(tableHeaderRow);
}

function createTableBody(tableId, data) {
    let table = document.getElementById(tableId);
    let tableBody = createAnyElement("tbody");
    table.appendChild(tableBody);
    let tableContent = getObjectsFromJSON(data);
    for (let values of Object.values(tableContent)) {
        let row = createAnyElement("tr");
        createTableCells(row, values, data);
        tableBody.appendChild(row);
    }
}

function createTableCells(row, content, data) {
    let columnNames = getColumnNames(data);
    for (let key of columnNames) {
        let cell = createAnyElement("td");
        if (!content[key]) {
            cell.innerHTML = "null";
        } else {
            cell.innerHTML = content[key];
        }
        row.appendChild(cell);
    }
}

function getObjectsFromJSON(data) {
    return JSON.parse(data);
}

function getColumnNames(data) {
    let items = getObjectsFromJSON(data);
    let columnNames = [];
    for (let item of items) {
        let keys = Object.keys(item);
        for (let key of keys) {
            if (!columnNames.includes(key)) {
                columnNames.push(key);
            }
        }
    }
    return columnNames;
}

function findAllItems() {
    createHtmlTable("allItemsRow", "allItemsTable", items);
}

function findByItemName() {

}

function validateItemName() {

}

function findInventroyItemByInventoryId() {

}

function validateInvewntoryId() {

}

function validateEmail() {

}

function validatePassword() {

}
