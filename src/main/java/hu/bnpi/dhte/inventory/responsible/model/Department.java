package hu.bnpi.dhte.inventory.responsible.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "department")
@Table(name = "departments")
public class Department extends Responsible {

    @NotNull
    @OneToOne
    @JoinColumn(name = "leader_id")
    private Employee leader;

    public Department(long id, String name, List<InventoryItem> items, Employee leader) {
        super(id, name, items);
        this.leader = leader;
    }

    public Department(String name, Employee leader) {
        super(name);
        this.leader = leader;
    }
}
