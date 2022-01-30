package com.istiak.learningspringboot.repository;

import com.istiak.learningspringboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//extends JpaRepository as it gives a lot of functionality
//need to pass in entity, in this case its Department entity
//need to pass in the type of the primary key, in this case its Long
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //returns Department
    public Department findByDepartmentName(String departmentName);

    public Department findByDepartmentNameIgnoreCase(String departmentName);

}
