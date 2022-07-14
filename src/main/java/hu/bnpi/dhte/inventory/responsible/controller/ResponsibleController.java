package hu.bnpi.dhte.inventory.responsible.controller;

import hu.bnpi.dhte.inventory.responsible.dtos.*;
import hu.bnpi.dhte.inventory.responsible.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/responsible")
public class ResponsibleController {

    private ResponsibleService service;

    @GetMapping(value = "/{name}")
    public List<ResponsibleDto> findResponsible(@PathVariable("name") Optional<String> name) {
        return service.findResponsible(name);
    }

    @GetMapping(value = "/employees/{name}")
    public List<EmployeeDto> findEmployees(@PathVariable("name") Optional<String> name) {
        return service.findEmployees(name);
    }

    @GetMapping(value = "/departments/{name}")
    public List<DepartmentDto> findDepartments(@PathVariable("name") Optional<String> name) {
        return service.findDepartments(name);
    }

    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto saveEmployee(@Valid @RequestBody SaveEmployeeCommand command) {
        return service.saveEmployee(command);
    }

    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto saveDepartment(@Valid @RequestBody SaveDepartmentCommand command) {
        return service.saveDepartment(command);
    }
}
