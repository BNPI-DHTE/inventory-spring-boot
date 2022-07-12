package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
