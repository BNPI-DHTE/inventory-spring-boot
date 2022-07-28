package hu.bnpi.dhte.inventory.auth.controller;

import hu.bnpi.dhte.inventory.auth.dtos.InventoryAppUserDetails;
import hu.bnpi.dhte.inventory.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private UserService service;

    @GetMapping
    public List<InventoryAppUserDetails> getUsers() {
        return service.getUsers();
    }
}
