package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Employee;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends ResponsibleBaseRepository<Employee> {
}
