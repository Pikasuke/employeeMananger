package com.moi.employeManager.Controllers;

import com.moi.employeManager.Entities.Employee;
import com.moi.employeManager.Service.EmployeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class EmployeControllers {

    private final EmployeService employeService;

    public EmployeControllers(EmployeService employeService) {
        this.employeService = employeService;
    }


    @GetMapping("/employes")
    public ResponseEntity<List<Employee>> getAllEmployes() {
        List<Employee> employees = employeService.findAllEmployes();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public Employee addEmploye (@RequestBody Employee employee) {
        return employeService.addEmploye(employee);
    }
}
