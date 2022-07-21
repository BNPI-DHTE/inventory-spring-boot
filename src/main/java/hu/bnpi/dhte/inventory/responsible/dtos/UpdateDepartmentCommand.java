package hu.bnpi.dhte.inventory.responsible.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateDepartmentCommand {

    @NotNull
    private long id;

    @NotBlank
    @Size(max = 50)
    private String responsibleId;

    @Size(min = 5, max = 150)
    private String name;

    @Size(min = 5, max = 150)
    private String nameOfLeader;
}
