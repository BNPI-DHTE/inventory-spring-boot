package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from departments", "delete from employees", "delete from responsible"})
class EmployeeRepositoryIT {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        Employee johnDoe = new Employee("001","John Doe", "johndoe@mail.com");
        Department financeDepartment = new Department("002", "Finance Department", johnDoe);
        Employee janeDoe = new Employee("003", "Jane Doe", "janedoe@mail.com");
        Department hrDepartment = new Department("004", "HR Department", janeDoe);
        employeeRepository.saveAll(List.of(johnDoe, janeDoe));
        departmentRepository.saveAll(List.of(financeDepartment, hrDepartment));
    }

    @Test
    void findAllEmployees() {
        assertThat(employeeRepository.findAllByName(Optional.empty()))
                .extracting(Employee::getName)
                .containsExactlyInAnyOrder("John Doe", "Jane Doe");
    }

    @Test
    void findEmployeeByName() {
        assertThat(employeeRepository.findAllByName(Optional.of("Jane Doe")))
                .extracting(Employee::getName)
                .containsExactly("Jane Doe");
    }

    @Test
    void findEmployeeByDepartmentName() {
        assertThat(employeeRepository.findAllByName(Optional.of("Finance Department")))
                .isEmpty();
    }
}