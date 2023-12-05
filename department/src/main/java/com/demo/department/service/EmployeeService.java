package com.demo.department.service;


import com.demo.department.entity.EmployeeEntity;
import com.demo.department.model.EmployeeModel;
import com.demo.department.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;


    public EmployeeEntity addEmployeeTODB(EmployeeModel model){
        EmployeeEntity entity= new EmployeeEntity();
        entity.setEmp_Name(model.getEmp_Name());
        entity.setEmp_salary(model.getEmp_Salary());
        entity.setEmp_Department(model.getEmp_Department());
        entity.setDeleted(false);
        entity.setAdded_Date(LocalDate.now());
            employeeRepo.save(entity);
        return entity;
    }


    public List<EmployeeEntity> getAllEmployees(){
        return this.employeeRepo.findAll();
    }
}
