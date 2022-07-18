package hu.bnpi.dhte.inventory.event.service;

import hu.bnpi.dhte.inventory.event.dtos.EventDetails;
import hu.bnpi.dhte.inventory.event.dtos.CreateEventCommand;
import hu.bnpi.dhte.inventory.event.exceptions.EventNotFoundException;
import hu.bnpi.dhte.inventory.event.mapper.EventMapper;
import hu.bnpi.dhte.inventory.event.model.Event;
import hu.bnpi.dhte.inventory.event.repositories.EventRepository;
import hu.bnpi.dhte.inventory.inventoryitem.exceptions.InventoryItemNotFoundException;
import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import hu.bnpi.dhte.inventory.inventoryitem.repositories.InventoryItemRepository;
import hu.bnpi.dhte.inventory.responsible.exceptions.ResponsibleNotFoundException;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import hu.bnpi.dhte.inventory.responsible.repositories.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class EventService {

    private EventRepository eventRepository;

    private InventoryItemRepository itemRepository;

    private ResponsibleRepository responsibleRepository;

    private EventMapper mapper;

    public List<EventDetails> findEventsByInventorId(String inventoryId) {
        return eventRepository.findAllByInventoryId(inventoryId).stream()
                .map(event -> mapper.toEventDetails(event))
                .toList();
    }

    public EventDetails findEventByNoteNumber(String noteNumber) {
        return mapper.toEventDetails(eventRepository.findByNoteNumber(noteNumber)
                .orElseThrow(() -> new EventNotFoundException(noteNumber)));
    }

    public List<EventDetails> findEventsByDateBetween(Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        LocalDate start = startDate.orElse(LocalDate.of(1980, 1, 1));
        LocalDate end = endDate.orElse(LocalDate.now());
        return eventRepository.findAllByDateBetween(start, end).stream()
                .map(event -> mapper.toEventDetails(event))
                .toList();
    }

    public EventDetails saveEvent(CreateEventCommand command) {
        List<InventoryItem> items = command.getInventoryIds().stream()
                .map(id -> itemRepository.findByInventoryId(id)
                        .orElseThrow(() -> new InventoryItemNotFoundException(id)))
                .toList();
        Responsible oldResponsible;
        Responsible newResponsible;
        if (responsibleRepository.findAllByName(Optional.of(command.getNameOfOldResponsible())).size() == 1) {
               oldResponsible = responsibleRepository.findAllByName(Optional.of(command.getNameOfOldResponsible())).get(0);
        } else {
            throw new ResponsibleNotFoundException(command.getNameOfOldResponsible());
        }
        if (responsibleRepository.findAllByName(Optional.of(command.getNameOfNewResponsible())).size() == 1) {
            newResponsible = responsibleRepository.findAllByName(Optional.of(command.getNameOfNewResponsible())).get(0);
        } else {
            throw new ResponsibleNotFoundException(command.getNameOfNewResponsible());
        }
        Event event = new Event(command.getDate(),
                command.getNoteNumber(),
                items,
                command.getEventType(),
                command.getDescription(),
                oldResponsible,
                newResponsible);
        eventRepository.save(event);
        return mapper.toEventDetails(event);
    }
}
