package com.app.EmployeManagement.Controller;

import com.app.EmployeManagement.Entity.User;
import com.app.EmployeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<User> findAll(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable UUID id){
        return userService.findById(id);
    }
}
