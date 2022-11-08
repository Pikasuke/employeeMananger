package com.moi.employeManager.Repository;

import com.moi.employeManager.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employee,Long> {

   // @Query("SELECT e FROM Employee e WHERE e.email = ?1")
}
