package com.moi.employeManager.Controllers;

import com.moi.employeManager.DTO.EmployeDTO;
import com.moi.employeManager.Entities.Employee;
import com.moi.employeManager.Service.EmployeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeControllers {

    private final EmployeServiceImpl employeService;

    public EmployeControllers(EmployeServiceImpl employeService) {
        this.employeService = employeService;
    }


    @GetMapping("/employes")
    public ResponseEntity<List<EmployeDTO>> getAllEmployes() {
        List<EmployeDTO> employesDto = employeService.findAllEmployes();
        return new ResponseEntity<>(employesDto, HttpStatus.OK);
    }

    @PostMapping("/employe")
    public EmployeDTO addEmploye (@RequestBody EmployeDTO employeDto) {
        return employeService.addEmploye(employeDto);
    }

    @DeleteMapping(path = "{employeId}")
    public void deleteEmploye (@PathVariable("employeId") Long employeId) {
        employeService.deleteEmployee(employeId);
    }

    @PutMapping("/employe")
    public EmployeDTO updateEmploye (@RequestBody EmployeDTO employeDto) {
       return employeService.updateEmploye(employeDto);
    }
}
