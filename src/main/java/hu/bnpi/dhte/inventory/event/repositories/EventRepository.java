package hu.bnpi.dhte.inventory.event.repositories;

import hu.bnpi.dhte.inventory.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
