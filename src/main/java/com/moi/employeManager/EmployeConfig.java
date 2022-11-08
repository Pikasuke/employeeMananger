package com.moi.employeManager;

import com.moi.employeManager.Entities.Employee;
import com.moi.employeManager.Repository.EmployeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeRepository employeRepository) {
        return args -> {
            Employee thomas = new Employee(
                    1L, "Thomas", "Thomas@gmail.com", "Developeur",
                    "0612345678", "", LocalDate.of(1988, Month.FEBRUARY, 14),"100");

            Employee marie = new Employee(
                    2L, "marie", "marie@gmail.com", "Developeuse",
                    "0612345678", "",LocalDate.of(2000, Month.JANUARY, 1), "100");

            employeRepository.saveAll(List.of(thomas, marie));
        };
    }
}
