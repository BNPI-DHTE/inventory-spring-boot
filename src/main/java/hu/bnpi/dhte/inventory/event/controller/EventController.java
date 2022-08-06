package hu.bnpi.dhte.inventory.event.controller;

import hu.bnpi.dhte.inventory.event.dtos.CreateEventCommand;
import hu.bnpi.dhte.inventory.event.dtos.EventDetails;
import hu.bnpi.dhte.inventory.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/events")
public class EventController {

    private EventService service;

    @GetMapping
    public List<EventDetails> findEventsInDateBetween(@RequestParam Optional<LocalDate> startDate, @RequestParam Optional<LocalDate> endDate) {
        return service.findEventsByDateBetween(startDate, endDate);
    }

    @GetMapping("/inventoryNumber")
    public List<EventDetails> findEventsByInventoryNumber(@RequestParam String inventoryNumber) {
        return service.findEventsByInventorNumber(inventoryNumber);
    }

    @GetMapping("/noteNumber")
    public EventDetails findEventByNoteNumber(@RequestParam String noteNumber) {
        return service.findEventByNoteNumber(noteNumber);
    }

    @PostMapping
    public EventDetails saveEvent(@Valid @RequestBody CreateEventCommand command) {
        return service.saveEvent(command);
    }
}
