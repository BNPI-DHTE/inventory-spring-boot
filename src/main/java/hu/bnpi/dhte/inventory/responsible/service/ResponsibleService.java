package hu.bnpi.dhte.inventory.responsible.service;

import hu.bnpi.dhte.inventory.responsible.dtos.*;
import hu.bnpi.dhte.inventory.responsible.exceptions.EmployeeNotFoundException;
import hu.bnpi.dhte.inventory.responsible.mapper.ResponsibleMapper;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import hu.bnpi.dhte.inventory.responsible.repositories.DepartmentRepository;
import hu.bnpi.dhte.inventory.responsible.repositories.EmployeeRepository;
import hu.bnpi.dhte.inventory.responsible.repositories.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class ResponsibleService {

    private ResponsibleRepository responsibleRepository;

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private ResponsibleMapper mapper;

    public List<EmployeeDto> findEmployees(Optional<String> name) {
        return employeeRepository.findAllByName(name).stream()
                .map(employee -> mapper.toEmployeeDto(employee))
                .toList();
    }

    public List<DepartmentDto> findDepartments(Optional<String> name) {
        return departmentRepository.findAllByName(name).stream()
                .map(department -> mapper.toDepartmentDto(department))
                .toList();
    }

    public List<ResponsibleDto> findResponsible(Optional<String> name) {
        return responsibleRepository.findAllByName(name).stream()
                .map(responsible -> mapper.toResponsibleDto(responsible))
                .toList();
    }

    public EmployeeDto saveEmployee(SaveEmployeeCommand command) {
        Employee employee = new Employee(command.getName(), command.getEmail());
        employeeRepository.save(employee);
        return mapper.toEmployeeDto(employee);
    }

    public DepartmentDto saveDepartment(SaveDepartmentCommand command) {
        List<Employee> leader = employeeRepository.findAllByName(Optional.of(command.getName()));
        if (leader.size() != 1) {
            throw new EmployeeNotFoundException(command.getName());
        }
        Department department = new Department(command.getName(), leader.get(0));
        departmentRepository.save(department);
        return mapper.toDepartmentDto(department);
    }
}
