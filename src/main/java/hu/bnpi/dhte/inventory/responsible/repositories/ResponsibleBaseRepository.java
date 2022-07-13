package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ResponsibleBaseRepository<T extends Responsible> extends JpaRepository<Responsible, Long> {

    @Query("select r from #{#entityName} as r  where(:name is null or r.name = :name)")
    List<T> findAllByName(Optional<String> name);
}
