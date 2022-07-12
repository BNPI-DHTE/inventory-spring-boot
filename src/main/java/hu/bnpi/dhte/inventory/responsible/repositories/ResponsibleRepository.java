package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
}
