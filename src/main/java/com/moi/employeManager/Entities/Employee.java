package com.moi.employeManager.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
    private String employeeCode;
}
