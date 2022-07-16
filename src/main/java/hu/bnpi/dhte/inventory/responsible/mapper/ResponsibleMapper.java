package hu.bnpi.dhte.inventory.responsible.mapper;

import hu.bnpi.dhte.inventory.responsible.dtos.DepartmentDetails;
import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDetails;
import hu.bnpi.dhte.inventory.responsible.dtos.ResponsibleDetails;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResponsibleMapper {

    EmployeeDetails toEmployeeDetails(Employee employee);

    @Mapping(target = "leaderName", source = "leader.name")
    @Mapping(target = "leaderEmail", source = "leader.email")
    DepartmentDetails toDepartmentDetails(Department department);

    ResponsibleDetails toResponsibleDetails(Responsible responsible);
}
