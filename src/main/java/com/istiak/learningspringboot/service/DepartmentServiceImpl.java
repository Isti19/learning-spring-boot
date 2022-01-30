package com.istiak.learningspringboot.service;

import com.istiak.learningspringboot.entity.Department;
import com.istiak.learningspringboot.error.DepartmentNotFoundException;
import com.istiak.learningspringboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    //autowiring
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {

        //save method takes in entity, in this case its department
        //this will make department save to the database
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {

        //findAll method will get departments as a list and send it back
        //will get all departments that are available in our database
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {

        //need to use department id as input param
        //find by id returns optional
        //to get value of department we have to use .get()
//        return departmentRepository.findById(departmentId).get();
        Optional<Department> department = departmentRepository.findById(departmentId);

        //if department is not found then do this
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }

        //if department is found do this
        return department.get();
    }

    @Override
    public void deleteDepartmentsById(Long departmentId) {
        //returns void that's why we are not returning directly
        //returning string instead
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {

        //get department that's already in the db
        //get that particular object and then whatever changes have been made in that department
        //update with that particular value

        Department depDB = departmentRepository.findById(departmentId).get();

        //check if any of params are null
        //if there's a value then we can update the depDB object
        //checking if anything is not null and not blank
        //then set the value, otherwise skip it
        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
//        return departmentRepository.findByDepartmentName(departmentName);
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
