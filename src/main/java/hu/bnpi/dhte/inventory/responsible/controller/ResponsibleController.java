package hu.bnpi.dhte.inventory.responsible.controller;

//import hu.bnpi.dhte.inventory.responsible.dtos.EmployeeDto;
import hu.bnpi.dhte.inventory.responsible.service.ResponsibleService;
import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/responsible")
public class ResponsibleController {

    private ResponsibleService service;

//    @GetMapping(value = "/employees")
//    public List<EmployeeDto> findEmployees() {
//        return service.findEmployees();
//    }
}
