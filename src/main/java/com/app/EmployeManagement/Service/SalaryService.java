package com.app.EmployeManagement.Service;

import com.app.EmployeManagement.Entity.Salary;
import com.app.EmployeManagement.Repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;
    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public List<Salary> getSalaries(){
        return salaryRepository.findAll();
    }
}
