package com.moi.employeManager.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor @AllArgsConstructor @Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name ;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageURL;
    private LocalDate dob;
    @Transient
    private Integer age;
    private String employeeCode;

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Employee(String name, String email, String jobTitle, String phone, String imageURL, LocalDate dob, String employeeCode) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageURL = imageURL;
        this.dob = dob;
        this.employeeCode = employeeCode;
    }
    public Employee(Long id, String name, String email, String jobTitle, String phone, String imageURL, LocalDate dob, String employeeCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageURL = imageURL;
        this.dob = dob;
        this.employeeCode = employeeCode;
    }
}
