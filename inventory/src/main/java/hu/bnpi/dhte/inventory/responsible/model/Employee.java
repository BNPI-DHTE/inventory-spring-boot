package hu.bnpi.dhte.inventory.responsible.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
}
