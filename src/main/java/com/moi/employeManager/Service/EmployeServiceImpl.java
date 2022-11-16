package com.moi.employeManager.Service;

import com.moi.employeManager.DTO.EmployeDTO;
import com.moi.employeManager.Entities.Employee;
import com.moi.employeManager.Repository.EmployeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeServiceImpl implements EmployeeService {

    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public EmployeDTO addEmploye(EmployeDTO employeDTO) {
        employeDTO.setDob(LocalDate.now());
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeDTO, employee);
        emailExist(employee);
        // employee.setEmployeeCode(UUID.randomUUID().toString());
        employeRepository.save(employee);
        return employeDTO;
    }

    @Transactional
    public EmployeDTO updateEmploye (EmployeDTO employeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeDto, employee);
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
            employeRepository.save(employee1.get());
            return employeDto;
        }
        return addEmploye(employeDto);
    }

    public List<EmployeDTO> findAllEmployes() {
        List<Employee> employees =  employeRepository.findAll();
        List<EmployeDTO> employeDTOS = employees.stream()
                .map(emp -> new EmployeDTO(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getJobTitle(),
                        emp.getPhone(),
                        emp.getImageURL(),
                        emp.getDob(),
                        emp.getAge(),
                        emp.getEmployeeCode()
                )).collect(Collectors.toList());
        return employeDTOS;
    }

    private Long id;
    private String name ;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageURL;
    private LocalDate dob;
    private Integer age;
    private String employeeCode;

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
