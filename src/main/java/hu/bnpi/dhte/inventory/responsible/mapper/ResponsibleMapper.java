package hu.bnpi.dhte.inventory.responsible.mapper;

import hu.bnpi.dhte.inventory.responsible.dtos.DepartmentDto;
import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDto;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResponsibleMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(target = "leaderName", source = "department.leader.name")
    DepartmentDto toDepartmentDto(Department department);
}
