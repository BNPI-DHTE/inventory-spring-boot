package hu.bnpi.dhte.inventory.event.repositories;

import hu.bnpi.dhte.inventory.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
