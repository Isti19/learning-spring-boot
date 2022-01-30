package com.istiak.learningspringboot.controller;

import com.istiak.learningspringboot.entity.Department;
import com.istiak.learningspringboot.error.DepartmentNotFoundException;
import com.istiak.learningspringboot.service.DepartmentService;
import com.istiak.learningspringboot.service.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    //creating object
    //@Autowired
    //means autowire the particular object that you have in your spring container to this reference which I have created
    //so that object will be attached (autowired) to this particular reference that we have created
    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);


    //public method which returns the Department object which is created
    //saveDepartment(@RequestBody Department department)
    //passing request body as well (entire JSON object)
    //want to convert JSON object into entity (in this case Department entity)
    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        //@RequestBody is used to tell Spring that whatever the data it's getting as a request body
        //whatever JSON it's getting, get it and convert it into a particular object
        //in this case its Department

        //call the service layer, to pass this data
        //from that service layer, do the business logic
        //after doing that we can call the repository layer to save that particular layer over here

        //passing in department object that we are getting as param
        //logger is useful for debugging
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return departmentService.saveDepartment(department);
    }


    //get all departments from the database
    //passing in List of departments (entity)
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        //not taking any input params as we need to send all the data back
        LOGGER.info("Inside fetchDepartmentList of DepartmentController");
        return departmentService.fetchDepartmentList();

    }


    //get department based on the id
    //fetchDepartmentById() takes in input param
    //@PathVariable maps id
    @GetMapping("departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {

        return departmentService.fetchDepartmentById(departmentId);

    }

    //delete department by id
    @DeleteMapping("departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){

        departmentService.deleteDepartmentsById(departmentId);

        return "Department deleted successfully";
    }


    //update department method
    @PutMapping("departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        //need to take in id and request body as well

        return departmentService.updateDepartment(departmentId, department);
    }

    //fetch department by name
    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);

    }




}
