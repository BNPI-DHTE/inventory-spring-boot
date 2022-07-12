package hu.bnpi.dhte.inventory.responsible.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "employee")
@Table(name = "employees")
public class Employee extends Responsible {

    @NotBlank
    @Email
    @Column(length = 150, nullable = false)
    private String email;

    public Employee(long id, String name, List<InventoryItem> items, String email) {
        super(id, name, items);
        this.email = email;
    }

    public Employee(String name, String email) {
        super(name);
        this.email = email;
    }
}
