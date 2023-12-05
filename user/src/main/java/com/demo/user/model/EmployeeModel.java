package com.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private Long emp_Id;
    private String emp_Name;
    private Double emp_Salary;
    private String emp_Department;

    private boolean deleted;

    private LocalDate added_Date;
}
