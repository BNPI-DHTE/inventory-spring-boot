package hu.bnpi.dhte.inventory.responsible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
}
