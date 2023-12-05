package com.demo.department.controller;


import com.demo.department.entity.EmployeeEntity;
import com.demo.department.model.EmployeeModel;
import com.demo.department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sivaK
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeEntity> addEmpIntoDb(@RequestBody EmployeeModel model){
        EmployeeEntity status=employeeService.addEmployeeTODB(model);
        if(status!=null){
            return  new ResponseEntity<EmployeeEntity>(status, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeEntity>> getAllEMployeesFromDb(){
        List<EmployeeEntity> allEmp=employeeService.getAllEmployees();
        if(allEmp.isEmpty()||allEmp==null){
            return  new ResponseEntity<List<EmployeeEntity>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<EmployeeEntity>>(allEmp,HttpStatus.OK);
    }

}
