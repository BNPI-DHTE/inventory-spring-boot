package hu.bnpi.dhte.inventory.responsible.repositories;

import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
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
class ResponsibleRepositoryIT {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @BeforeEach
    void setUp() {
        Employee johnDoe = new Employee("001", "John Doe", "johndoe@mail.com");
        Department financeDepartment = new Department("002", "Finance Department", johnDoe);
        Employee janeDoe = new Employee("003", "Jane Doe", "janedoe@mail.com");
        Department hrDepartment = new Department("004", "HR Department", janeDoe);
        employeeRepository.saveAll(List.of(johnDoe, janeDoe));
        departmentRepository.saveAll(List.of(financeDepartment, hrDepartment));
    }

    @Test
    void findAllResponsibleTest() {
        assertThat(responsibleRepository.findAllByName(Optional.empty()))
                .extracting(Responsible::getName)
                .containsExactlyInAnyOrder("John Doe", "Jane Doe", "Finance Department", "HR Department");
    }

    @Test
    void findEmployeeAsResponsibleByName() {
        assertThat(responsibleRepository.findAllByName(Optional.of("Jane Doe")))
                .extracting(Responsible::getName)
                .containsExactly("Jane Doe");
    }

    @Test
    void findDepartmentAsResponsibleByName() {
        assertThat(responsibleRepository.findAllByName(Optional.of("Finance Department")))
                .extracting(Responsible::getName)
                .containsExactly("Finance Department");
    }

    @Test
    void findDepartmentByNameDoesNotExists() {
        assertThat(responsibleRepository.findAllByName(Optional.of("Non-Existent")))
                .isEmpty();
    }
}