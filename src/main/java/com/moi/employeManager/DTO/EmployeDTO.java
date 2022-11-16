package com.moi.employeManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Data
public class EmployeDTO {

    private Long id;
    private String name ;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageURL;
    private LocalDate dob;
    private Integer age;
    private String employeeCode;
}
