package com.javaadr.renderapi.integration;

import com.javaadr.renderapi.Entity.Salary;
import com.javaadr.renderapi.Entity.User;
import com.javaadr.renderapi.Repository.SalaryRepository;
import com.javaadr.renderapi.Service.SalaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalaryIT {
    @Mock
    private SalaryRepository salaryRepository;
    private SalaryService salaryService;
    @BeforeEach
    public void setUp() {
        salaryService = new SalaryService(salaryRepository);
    }
    @Test
    public void testGetSalary() throws Exception {
        Salary salary1 = new Salary(1, 1000.0, 50.0, new Timestamp(new Date().getTime()));
        Salary salary2 = new Salary(2, 1500.0, 75.0, new Timestamp(new Date().getTime()));
        when(salaryRepository.findAll()).thenReturn(Arrays.asList(salary1, salary2));

        List<Salary> salaries = salaryService.getSalaries();

        assertThat(salaries).isNotNull();
        assertThat(salaries).hasSize(2);
        assertThat(salaries.get(0).getAmount()).isEqualTo(1000.0);
        assertThat(salaries.get(1).getRiseup()).isEqualTo(75.0);
    }

}
