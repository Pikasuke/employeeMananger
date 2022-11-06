package com.moi.employeManager.Service;

import com.moi.employeManager.Entities.Employee;
import com.moi.employeManager.Repository.EmployeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Employee addEmploye(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeRepository.save(employee);
    }

    public Employee updateEmploye (Employee employee) {
        Optional<Employee> employee1 = employeRepository.findById(employee.getId());
        if (employee1.isPresent()) {
            if (employee.getName()!= "") employee1.get().setName(employee.getName());
            if (employee.getEmail()!= "") employee1.get().setEmail(employee.getEmail());
            if (employee.getPhone()!= "") employee1.get().setPhone(employee.getPhone());
            if (employee.getJobTitle()!= "") employee1.get().setJobTitle(employee.getJobTitle());
            if (employee.getImageURL() != "") employee1.get().setImageURL(employee.getImageURL());
            if (employee.getEmployeeCode() != "") employee1.get().setEmployeeCode(employee.getEmployeeCode());
            return employeRepository.save(employee1.get());
        }
        return addEmploye(employee);
    }

    public List<Employee> findAllEmployes() {
        return employeRepository.findAll();
    }

    public void deleteEmployee(Employee employee) {
        employeRepository.deleteById(employee.getId());
    }

    public Optional<Employee> findEmployeeById (Employee employee) {
        return employeRepository.findById(employee.getId());
    }
}
