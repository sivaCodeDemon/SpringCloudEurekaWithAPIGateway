package com.demo.controller;

import com.demo.entity.PaymentEntity;
import com.demo.model.DepartmentModel;
import com.demo.model.PaymentModel;
import com.demo.service.PaymentService;
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
@Slf4j
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/addPayments")
    public ResponseEntity<PaymentEntity> addPayments(@RequestBody PaymentModel paymentModel){
        PaymentEntity entity= paymentService.addPaymentIntoDb(paymentModel);
        if(entity==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<PaymentEntity>(entity,HttpStatus.OK);
    }

    @GetMapping("/findPaymentsById/{id}")
    public ResponseEntity<PaymentEntity> findPaymnetsById(@PathVariable Long id){
        PaymentEntity entity =paymentService.getPaymentbasedOnId(id);
        if(entity==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<PaymentEntity>(entity,HttpStatus.OK);
    }


    @GetMapping("findAllPayments")
    public  ResponseEntity<List<PaymentEntity>> finaAllPayments(){
        List<PaymentEntity> list=paymentService.getAllPayments();
        if(list==null||list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<List<PaymentEntity>>(list,HttpStatus.OK);
    }

    @PutMapping("/updateBasedOnId/{id}")
    public  ResponseEntity<String> updatePayments(@RequestBody PaymentModel paymentModel
            ,@PathVariable  Long id){
        String status=paymentService.updatePaymentData(paymentModel,id);
        if(status==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<String>(status,HttpStatus.OK);
    }

    @DeleteMapping("deleteBasesOnId/{id}")
    public ResponseEntity<String> deletePaymnets(@PathVariable Long id){
        String status=paymentService.deletePaymentBasedOnId(id);
        if(status==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<String>(status,HttpStatus.OK);
    }

    @GetMapping("/allDepartments")
    public  ResponseEntity<List<DepartmentModel>> getAllDepartment(){
        DepartmentModel[] model= restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/all",DepartmentModel[].class);
        List<DepartmentModel> modelList= Arrays.asList(model);
        if(modelList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<DepartmentModel>>(modelList,HttpStatus.OK);
    }
}
