package hu.bnpi.dhte.inventory.event.dtos;

import hu.bnpi.dhte.inventory.event.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveEventCommand {

    //TODO Custom validations on responsible, inventoryIds;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotBlank
    @Size(max = 50)
    private String noteNumber;

    @NotEmpty
    private List<String> inventoryIds;

    @NotNull
    private EventType eventType;

    private String description;

    private String nameOfOldResponsible;

    private String nameOfNewResponsible;
}
