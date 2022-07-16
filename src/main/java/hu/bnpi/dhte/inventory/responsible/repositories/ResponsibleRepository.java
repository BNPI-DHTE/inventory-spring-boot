package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponsibleRepository extends ResponsibleBaseRepository<Responsible> {

    List<Responsible> findAllByName(Optional<String> name);
}
