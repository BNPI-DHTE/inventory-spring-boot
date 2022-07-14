package hu.bnpi.dhte.inventory.responsible.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
public class SaveDepartmentCommand {

    @NotBlank
    @Size(min = 5, max = 150)
    private String name;

    @NotBlank
    @Size(min = 5, max = 150)
    private String nameOfLeader;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfLeader() {
        return nameOfLeader;
    }

    public void setNameOfLeader(String nameOfLeader) {
        this.nameOfLeader = nameOfLeader;
    }
}
