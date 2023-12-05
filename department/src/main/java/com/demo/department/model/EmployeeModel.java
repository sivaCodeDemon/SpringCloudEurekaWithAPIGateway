package com.demo.department.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private Long id;
    private String emp_Name;
    private Double emp_Salary;
    private String emp_Department;

}
