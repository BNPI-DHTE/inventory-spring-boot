package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ResponsibleBaseRepository<T extends Responsible> extends JpaRepository<T, Long> {

    @Query("select r from #{#entityName} as r where(:name is null or upper(r.name) like concat('%', upper(:name), '%'))")
    List<T> findAllByName(@Param("name") Optional<String> name);

    @Query("select r from  #{#entityName} as r where :id = r.id")
    Optional<T> findById(long id);

    @Query("select r from #{#entityName} as r where :responsibleNumber = r.responsibleNumber")
    List<T> findAllByResponsibleNumber(@Param("responsibleNumber") String responsibleNumber);
}
