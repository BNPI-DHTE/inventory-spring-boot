package hu.bnpi.dhte.inventory.event.repositories;

import hu.bnpi.dhte.inventory.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select distinct e from Event e left join fetch e.items as i where :inventoryId = i.inventoryId")
    List<Event> findAllByInventoryId(@Param("inventoryId") String inventoryId);

    Optional<Event> findByNoteNumber (String noteNumber);

    List<Event> findAllByDateBetween (LocalDate startDate, LocalDate endDate);
}
