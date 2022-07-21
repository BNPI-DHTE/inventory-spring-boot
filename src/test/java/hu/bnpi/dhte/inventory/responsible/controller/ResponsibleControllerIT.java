package hu.bnpi.dhte.inventory.responsible.controller;

import hu.bnpi.dhte.inventory.responsible.dtos.*;
import hu.bnpi.dhte.inventory.responsible.model.Department;
import hu.bnpi.dhte.inventory.responsible.model.Employee;
import hu.bnpi.dhte.inventory.responsible.repositories.ResponsibleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from departments", "delete from employees", "delete from responsible"})
class ResponsibleControllerIT {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ResponsibleRepository repository;

    private Employee janeDoe;

    private Employee johnDoe;

    private Department hrDepartment;

    @BeforeEach
    void setUp() {
        janeDoe = new Employee("001", "Jane Doe", "janeDoe@mail.com");
        johnDoe = new Employee("002","John Doe", "johnDoe@mail.com");
        Employee jackDoe = new Employee("003", "Jack Doe", "jackDoe@mail.com");
        Employee jillDoe = new Employee("004","Jill Doe", "jillDoe@mail.com");
        Employee joeDoe = new Employee("005","Joe Doe", "joeDoe@mail.com");
        repository.saveAll(List.of(janeDoe, johnDoe, jackDoe, jillDoe, joeDoe));

        hrDepartment = new Department("006","HR Department", janeDoe);
        Department financeDepartment = new Department("007", "Finance Department", johnDoe);
        repository.saveAll(List.of(hrDepartment, financeDepartment));
    }

    @Test
    void findResponsibleTest() {
        client.get()
                .uri("/api/responsible")
                .exchange()
                .expectBodyList(ResponsibleDetails.class)
                .hasSize(7)
                .value(list -> assertThat(list)
                        .extracting(ResponsibleDetails::getName)
                        .contains("John Doe", "Jill Doe", "HR Department", "Finance Department"));
    }

    @Test
    void findEmployeesTest() {
        client.get()
                .uri("/api/responsible/employees")
                .exchange()
                .expectBodyList(EmployeeDetails.class)
                .hasSize(5)
                .value(list -> assertThat(list)
                        .extracting(EmployeeDetails::getName)
                        .containsExactlyInAnyOrder("Jane Doe", "John Doe", "Jill Doe", "Jack Doe", "Joe Doe"));
    }

    @Test
    void findDepartmentsTest() {
        client.get()
                .uri("/api/responsible/departments")
                .exchange()
                .expectBodyList(DepartmentDetails.class)
                .hasSize(2)
                .value(list -> assertThat(list)
                        .extracting(DepartmentDetails::getLeaderEmail)
                        .containsExactlyInAnyOrder("janeDoe@mail.com", "johnDoe@mail.com"));
    }

    @Test
    void saveEmployeeTest() {
        client.post()
                .uri("/api/responsible/employees")
                .bodyValue(new SaveEmployeeCommand("008", "Jacob Doe", "jacobDoe@mail.com"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EmployeeDetails.class)
                .value(employeeDto -> assertEquals("jacobDoe@mail.com", employeeDto.getEmail()));
    }

    @Test
    void saveDepartmentTest() {
        client.post()
                .uri("/api/responsible/departments")
                .bodyValue(new SaveDepartmentCommand("008", "Legal Department", "Jill Doe"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(DepartmentDetails.class)
                .value(departmentDto -> assertEquals("jillDoe@mail.com", departmentDto.getLeaderEmail()));
    }

    @Test
    void updateEmployeeTest() {
        client.put()
                .uri("/api/responsible/employees")
                .bodyValue(new UpdateEmployeeCommand(janeDoe.getId(), "", "Jane Smith Doe", "janeSmithDoe@mail.com"))
                .exchange()
                .expectBody(EmployeeDetails.class)
                .value(employeeDetails -> assertEquals("janeSmithDoe@mail.com", employeeDetails.getEmail()))
                .value(employeeDetails -> assertEquals("001", employeeDetails.getResponsibleId()));
    }

    @Test
    void updateDepartmentTest() {
        client.put()
                .uri("/api/responsible/departments")
                .bodyValue(new UpdateDepartmentCommand(hrDepartment.getId(), "", "Human Resources Department", "Joe Doe"))
                .exchange()
                .expectBody(DepartmentDetails.class)
                .value(departmentDto -> assertEquals("joeDoe@mail.com", departmentDto.getLeaderEmail()))
                .value(departmentDto -> assertEquals("Human Resources Department", departmentDto.getName()))
                .value(departmentDto -> assertEquals("006", departmentDto.getResponsibleId()));
    }

    @Test
    void removeResponsibleTest() {
        client.delete()
                .uri("/api/responsible/" + hrDepartment.getId())
                .exchange()
                .expectStatus().isNoContent();

        client.get()
                .uri("/api/responsible/departments")
                .exchange()
                .expectBodyList(DepartmentDetails.class)
                .hasSize(1)
                .value(list -> assertThat(list)
                        .extracting(DepartmentDetails::getName)
                        .containsExactlyInAnyOrder("Finance Department"));
    }
}