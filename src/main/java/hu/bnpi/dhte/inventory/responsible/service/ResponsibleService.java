package hu.bnpi.dhte.inventory.responsible.service;

import hu.bnpi.dhte.inventory.responsible.dtos.*;
import hu.bnpi.dhte.inventory.responsible.exceptions.DepartmentNotFoundException;
import hu.bnpi.dhte.inventory.responsible.exceptions.EmployeeNotFoundException;
import hu.bnpi.dhte.inventory.responsible.exceptions.ResponsibleCanNotDeleteException;
import hu.bnpi.dhte.inventory.responsible.exceptions.ResponsibleNotFoundException;
import hu.bnpi.dhte.inventory.responsible.mapper.ResponsibleMapper;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
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

    public List<EmployeeDetails> findEmployees(Optional<String> name) {
        return employeeRepository.findAllByName(name).stream()
                .map(responsible -> mapper.toEmployeeDetails(responsible))
                .toList();
    }

    public List<DepartmentDetails> findDepartments(Optional<String> name) {
        return departmentRepository.findAllByName(name).stream()
                .map(responsible -> mapper.toDepartmentDetails(responsible))
                .toList();
    }

    public List<ResponsibleDetails> findResponsible(Optional<String> name) {
        return responsibleRepository.findAllByName(name).stream()
                .map(responsible -> mapper.toResponsibleDetails(responsible))
                .toList();
    }

    public EmployeeDetails saveEmployee(SaveEmployeeCommand command) {
        Employee employee = new Employee(command.getResponsibleId(), command.getName(), command.getEmail());
        employeeRepository.save(employee);
        return mapper.toEmployeeDetails(employee);
    }

    public DepartmentDetails saveDepartment(SaveDepartmentCommand command) {
        List<Employee> leader = getDepartmentLeader(command.getResponsibleIdOfLeader());
        Department department = new Department(command.getResponsibleId(), command.getName(), leader.get(0));
        departmentRepository.save(department);
        return mapper.toDepartmentDetails(department);
    }

    public EmployeeDetails updateEmployee(UpdateEmployeeCommand command) {
        Employee employee = employeeRepository.findById(command.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(command.getId()));
        if (command.getName() != null && !command.getName().isBlank()) {
            employee.setName(command.getName());
        }
        if (command.getEmail() != null && !command.getEmail().isBlank()) {
            employee.setEmail(command.getEmail());
        }
        return mapper.toEmployeeDetails(employee);
    }

    public DepartmentDetails updateDepartment(UpdateDepartmentCommand command) {
        Department department = departmentRepository.findById(command.getId())
                .orElseThrow(() -> new DepartmentNotFoundException(command.getId()));
        if (command.getName() != null && !command.getName().isBlank()) {
            department.setName(command.getName());
        }
        if (command.getResponsibleIdOfLeader() != null && !command.getResponsibleIdOfLeader().isBlank()) {
            List<Employee> leader = getDepartmentLeader(command.getResponsibleIdOfLeader());
            if (leader.size() != 1) {
                throw new EmployeeNotFoundException(command.getResponsibleIdOfLeader());
            }
            department.setLeader(leader.get(0));
        }
        return mapper.toDepartmentDetails(department);
    }

    private List<Employee> getDepartmentLeader(String responsibleId) {
        List<Employee> leader = employeeRepository.findAllByResponsibleId(responsibleId).stream()
                .map(Employee.class::cast)
                .toList();
        if (leader.size() != 1) {
            throw new EmployeeNotFoundException(responsibleId);
        }
        return leader;
    }

    public void deleteResponsible(long id) {
        Responsible result = responsibleRepository.findById(id)
                .orElseThrow(() -> new ResponsibleNotFoundException(id));
        if (!result.getItems().isEmpty()) {
            throw new ResponsibleCanNotDeleteException(id);
        }
        if (!departmentRepository.findDepartmentByLeader_Name(result.getName()).isEmpty()) {
            throw new ResponsibleCanNotDeleteException(result.getName());
        }
        responsibleRepository.delete(result);
    }
}
