package hu.bnpi.dhte.inventory.responsible.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "department")
@Table(name = "departments")
@PrimaryKeyJoinColumn(name = "resp_id")
public class Department extends Responsible {

    @OneToOne
    @JoinColumn(name = "leader_id")
    private Employee leader;

    public Department(long id, String responsibleNumber, String name, List<InventoryItem> items, Employee leader) {
        super(id, responsibleNumber, name, items);
        this.leader = leader;
    }

    public Department(String responsibleNumber, String name, Employee leader) {
        super(responsibleNumber, name);
        this.leader = leader;
    }

    public Department(String responsibleNumber, String name) {
        super(responsibleNumber, name);
    }
}
