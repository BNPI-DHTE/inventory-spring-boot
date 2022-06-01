package hu.bnpi.dhte.inventory.repositories;

import hu.bnpi.dhte.inventory.models.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
}
