package hu.bnpi.dhte.inventory.responsible.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveDepartmentCommand {

    @NotBlank
    @Size(max = 50)
    private String responsibleNumber;

    @NotBlank
    @Size(min = 5, max = 150)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String responsibleNumberOfLeader;
}
