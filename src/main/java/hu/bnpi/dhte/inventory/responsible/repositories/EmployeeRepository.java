package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
