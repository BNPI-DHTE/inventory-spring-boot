package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends ResponsibleBaseRepository<Department> {

    List<Department> findDepartmentByLeader_Name(String nameOfLeader);
}
