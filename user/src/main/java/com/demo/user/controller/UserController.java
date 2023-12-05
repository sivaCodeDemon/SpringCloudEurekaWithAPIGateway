package com.demo.user.controller;

import com.demo.user.model.DepartmentModel;
import com.demo.user.model.EmployeeModel;
import com.demo.user.model.FindDepartmentByUser;
import com.demo.user.entity.UserEntity;
import com.demo.user.model.Department;
import com.demo.user.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author sivaK
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/addUser")
    public UserEntity insertUserData(@RequestBody UserEntity userEntity){
        return this.userRepo.save(userEntity);
    }

    @GetMapping("/getUserAndDepartmentById/{id}")
    public FindDepartmentByUser findDepartmentByUserId(@PathVariable("id") long id){
        FindDepartmentByUser findDepartmentByUser=new FindDepartmentByUser();
        UserEntity userEntity=this.userRepo.findById(id).get();

        DepartmentModel departmentModel=restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+id,DepartmentModel.class);
        findDepartmentByUser.setUserEntity(userEntity);
        findDepartmentByUser.setDepartmentModel(departmentModel);
        return findDepartmentByUser;
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DepartmentModel>> getAllDepartments() {

        DepartmentModel[] dp = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/all", DepartmentModel[].class);

        List<DepartmentModel> departmentList = Arrays.asList(dp);
            if(departmentList.isEmpty()||departmentList==null){
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<List<DepartmentModel>>(departmentList,HttpStatus.OK);
    }


    @GetMapping("/getDepartmentById/{deptid}")
    public  ResponseEntity<DepartmentModel> getDepartmentBy(@PathVariable int deptid){
        DepartmentModel model= restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+deptid,DepartmentModel.class);
        if(model==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<DepartmentModel>(model, HttpStatus.OK);
    }

    @PostMapping("/addDeparment")
    public ResponseEntity<DepartmentModel> insert(@RequestBody DepartmentModel dept){

        DepartmentModel model=restTemplate.postForObject("http://DEPARTMENT-SERVICE/department/add",dept,DepartmentModel.class);
                if(model==null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return  new ResponseEntity<DepartmentModel>(model,HttpStatus.OK);
    }

    @PutMapping("/deptUpdateById/{id}")
    public String updateDepartment(@RequestBody Department model, @PathVariable Long id) {
        restTemplate.put("http://DEPARTMENT-SERVICE/department/update/" + id, model);
        return "UPDATED_DEPARTMENT";
    }

    @DeleteMapping("/deleteDepartmentById/{id}")
    public String DeleteDeparment(@PathVariable Long id){
        restTemplate.delete("http://DEPARTMENT-SERVICE/department/delete/" + id);
        return "DELETE_DEPARTMENT_SUCCESSFULLY";
    }

    @PostMapping("/addEmployeeFromEmployeeService")
    public ResponseEntity<EmployeeModel> addEmpObjectIntoEmpService(@RequestBody EmployeeModel model){
        EmployeeModel m=restTemplate.postForObject("http://DEPARTMENT-SERVICE/api/addEmployee",model,EmployeeModel.class);
       if(m!=null) {
           return new ResponseEntity<EmployeeModel>(m, HttpStatus.OK);
       }
       return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAllEMployeesFromDeptService")
    public ResponseEntity<List<EmployeeModel>> getAllEmp(){
        log.info("START :      getAllEmp    :UserController.java ");
       EmployeeModel[] all=restTemplate.getForObject("http://DEPARTMENT-SERVICE/api/allEmployees",EmployeeModel[].class);
        log.info("allEmployees"+all);
               List<EmployeeModel> emplist=Arrays.asList(all);
               log.info("listEmployees"+emplist);
       if(emplist.isEmpty()||emplist==null){
           log.info("MIDDLE :      getAllEmp    :UserController.java ");
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        log.info("END :      getAllEmp    :UserController.java ");
       return new ResponseEntity<List<EmployeeModel>>(emplist,HttpStatus.OK);
    }
}
