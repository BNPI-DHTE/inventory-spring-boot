package hu.bnpi.dhte.inventory.event.mapper;

import hu.bnpi.dhte.inventory.event.dtos.EventDetails;
import hu.bnpi.dhte.inventory.event.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDetails toEventDetails (Event event);
}
