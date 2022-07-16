package hu.bnpi.dhte.inventory.inventoryitem.dtos;

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
public class CreateKitCommand {

    @NotBlank
    @Size(max = 100)
    private String name;
}
