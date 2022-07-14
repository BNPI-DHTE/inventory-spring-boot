package hu.bnpi.dhte.inventory.responsible.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
public class SaveEmployeeCommand {

    @NotBlank
    @Size(min = 5, max = 150)
    private String name;

    @NotBlank
    @Email
    @Size(min = 8, max = 150)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
