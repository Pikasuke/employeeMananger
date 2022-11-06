package com.moi.employeManager.Repository;

import com.moi.employeManager.Controllers.EmployeControllers;
import com.moi.employeManager.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employee,Long> {
}
