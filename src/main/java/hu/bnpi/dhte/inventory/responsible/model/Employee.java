package hu.bnpi.dhte.inventory.responsible.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "employee")
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "resp_id")
public class Employee extends Responsible {

    @Email
    @Column(length = 150)
    private String email;

    public Employee(long id, String responsibleNumber, String name, List<InventoryItem> items, String email) {
        super(id, responsibleNumber, name, items);
        this.email = email;
    }

    public Employee(String responsibleNumber, String name, String email) {
        super(responsibleNumber, name);
        this.email = email;
    }

    public Employee(String responsibleNumber, String name) {
        super(responsibleNumber, name);
    }
}
