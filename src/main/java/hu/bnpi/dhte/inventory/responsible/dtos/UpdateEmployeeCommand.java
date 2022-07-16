package hu.bnpi.dhte.inventory.responsible.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateEmployeeCommand {

    @NotNull
    private long id;

    @Size(min = 5, max = 150)
    private String name;

    @Email
    @Size(min = 8, max = 150)
    private String email;

    public UpdateEmployeeCommand(long id, String email) {
        this.id = id;
        this.email = email;
    }
}
