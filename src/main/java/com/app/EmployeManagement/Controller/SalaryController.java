package com.app.EmployeManagement.Controller;

import com.app.EmployeManagement.Entity.Salary;
import com.app.EmployeManagement.Service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/all")
    public List<Salary> findAll(){
        return salaryService.getSalaries();
    }
}
