package com.moi.employeManager.Service;

import com.moi.employeManager.Entities.Employee;
import com.moi.employeManager.Repository.EmployeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Employee addEmploye(Employee employee) {
        emailExist(employee);
        // employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmploye (Employee employee) {
        Optional<Employee> employee1 = employeRepository.findById(employee.getId());
        if (employee1.isPresent()) {
            if (employee.getName()!= "" || employee.getName()!=null ) employee1.get().setName(employee.getName());
            if (employee.getEmail()!= "" || employee.getEmail()!=null) {
                emailExist(employee);
                employee1.get().setEmail(employee.getEmail());
            }
            if (employee.getPhone()!= "" || employee.getPhone()!=null) employee1.get().setPhone(employee.getPhone());
            if (employee.getJobTitle()!= "" || employee.getJobTitle()!=null) employee1.get().setJobTitle(employee.getJobTitle());
            if (employee.getImageURL() != "" || employee.getImageURL()!=null) employee1.get().setImageURL(employee.getImageURL());
            if (employee.getEmployeeCode() != "" || employee.getEmployeeCode()!=null) employee1.get().setEmployeeCode(employee.getEmployeeCode());
            return employeRepository.save(employee1.get());
        }
        return addEmploye(employee);
    }

    public List<Employee> findAllEmployes() {
        return employeRepository.findAll();
    }

    public void deleteEmployee(Long employeId) {
        boolean exist = employeRepository.existsById(employeId);
        if (!exist) {
            throw new IllegalStateException( " Employe with ID : " + employeId + "Does not exists ");
        }
        employeRepository.deleteById(employeId);
    }

    public Optional<Employee> findEmployeeById (Employee employee) {
        return employeRepository.findById(employee.getId());
    }

    public void emailExist (Employee employe) {
        List<String> emails = findAllEmployes().stream().map(e -> e.getEmail()).toList();
        if (emails.contains(employe.getEmail())) {
            throw new IllegalStateException(("Email already taken"));
        }
    }
}
