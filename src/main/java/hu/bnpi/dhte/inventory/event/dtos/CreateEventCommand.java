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
public class CreateEventCommand {

    //TODO Custom validations on responsible, inventoryIds;

    @NotNull(message = "Date cannot be null!")
    @PastOrPresent
    private LocalDate date;

    @Size(max = 50, message = "Note's serial number cannot be longer than 50 characters!")
    private String noteNumber;

    @NotEmpty(message = "List of inventory items cannot be empty!")
    private List<String> inventoryIds;

    @NotNull(message = "Event type cannot be null!")
    private EventType eventType;

    private String description;

    private String nameOfOldResponsible;

    private String nameOfNewResponsible;
}
