package hu.bnpi.dhte.inventory.responsible.service;

import hu.bnpi.dhte.inventory.responsible.dtos.DepartmentDto;
import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDto;
import hu.bnpi.dhte.inventory.responsible.dtos.ResponsibleDto;
import hu.bnpi.dhte.inventory.responsible.mapper.ResponsibleMapper;
import hu.bnpi.dhte.inventory.responsible.repositories.DepartmentRepository;
import hu.bnpi.dhte.inventory.responsible.repositories.EmployeeRepository;
import hu.bnpi.dhte.inventory.responsible.repositories.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private ResponsibleRepository responsibleRepository;

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private ResponsibleMapper responsibleMapper;

    public List<EmployeeDto> findEmployees(Optional<String> name) {
        return employeeRepository.findAll().stream()
                .map(employee -> responsibleMapper.toEmployeeDto(employee))
                .toList();
    }

    public List<DepartmentDto> findDepartments(Optional<String> name) {
        return departmentRepository.findAll().stream()
                .map(department -> responsibleMapper.toDepartmentDto(department))
                .toList();
    }

    public List<ResponsibleDto> findResponsible(Optional<String> name) {
        return responsibleRepository.findAll().stream()
                .map(responsible -> responsibleMapper.toResponsibleDto(responsible))
                .toList();
    }
}
