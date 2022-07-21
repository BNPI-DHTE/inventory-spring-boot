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
@PrimaryKeyJoinColumn(name = "responsible_id")
public class Department extends Responsible {

    @OneToOne
    @JoinColumn(name = "leader_id")
    private Employee leader;

    public Department(long id, String responsibleId, String name, List<InventoryItem> items, Employee leader) {
        super(id, responsibleId, name, items);
        this.leader = leader;
    }

    public Department(String responsibleId, String name, Employee leader) {
        super(responsibleId, name);
        this.leader = leader;
    }

    public Department(String responsibleId, String name) {
        super(responsibleId, name);
    }
}
