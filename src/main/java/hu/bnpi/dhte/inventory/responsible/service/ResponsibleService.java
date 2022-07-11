package hu.bnpi.dhte.inventory.responsible.service;

import hu.bnpi.dhte.inventory.responsible.mapper.ResponsibleMapper;
import hu.bnpi.dhte.inventory.responsible.repositories.DepartmentRepository;
import hu.bnpi.dhte.inventory.responsible.repositories.EmployeeRepository;
import hu.bnpi.dhte.inventory.responsible.repositories.ResponsibleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponsibleService {

    private ResponsibleRepository responsibleRepository;

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private ResponsibleMapper responsibleMapper;
}
