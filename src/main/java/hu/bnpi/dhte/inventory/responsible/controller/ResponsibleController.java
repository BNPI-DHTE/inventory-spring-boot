package hu.bnpi.dhte.inventory.responsible.controller;

import hu.bnpi.dhte.inventory.responsible.dtos.DepartmentDto;
import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDto;
import hu.bnpi.dhte.inventory.responsible.dtos.ResponsibleDto;
import hu.bnpi.dhte.inventory.responsible.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/responsible")
public class ResponsibleController {

    private ResponsibleService service;

    @GetMapping(value = "/employees/{name}")
    public List<EmployeeDto> findEmployees(@PathVariable("name") Optional<String> name) {
        return service.findEmployees(name);
    }

    @GetMapping(value = "/department/{name}")
    public List<DepartmentDto> findDepartments(@PathVariable("name") Optional<String> name) {
        return service.findDepartments(name);
    }

    @GetMapping(value = "/responsible/{name}")
    public List<ResponsibleDto> findResponsible(@PathVariable("name") Optional<String> name) {
        return service.findResponsible(name);
    }
}
