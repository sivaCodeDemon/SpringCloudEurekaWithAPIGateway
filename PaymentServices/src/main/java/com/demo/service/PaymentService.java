package com.demo.service;

import com.demo.entity.PaymentEntity;
import com.demo.model.PaymentModel;
import com.demo.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public PaymentEntity addPaymentIntoDb(PaymentModel paymentModel){
        PaymentEntity entity = new PaymentEntity();
        entity.setPayment_HolderName(paymentModel.getPayment_HolderName());
        entity.setPayment_Amount(paymentModel.getPayment_Amount());
        entity.setPayment_Type(paymentModel.getPayment_Type());
        paymentRepo.save(entity);
        return entity;
    }

    public PaymentEntity getPaymentbasedOnId(Long id){
        Optional<PaymentEntity> entity=paymentRepo.findById(id);
        if(entity.isPresent()){
        PaymentEntity m=entity.get();
        return  m;
        }
        return null;
    }

    public List<PaymentEntity> getAllPayments(){
        return this.paymentRepo.findAll();
    }

    public String updatePaymentData(PaymentModel model,Long id){
        Optional<PaymentEntity> entity= paymentRepo.findById(id);
        if(entity.isPresent()){
            PaymentEntity originalEntity=entity.get();
            originalEntity.setPayment_HolderName(model.getPayment_HolderName());
            originalEntity.setPayment_Amount(model.getPayment_Amount());
            originalEntity.setPayment_Type(model.getPayment_Type());
            paymentRepo.save(originalEntity);
            return "UPDATED_PAYMETS_SUCCESSFULLY";
        }
        return "FAILED_UPDATING";
    }

    public String deletePaymentBasedOnId(Long id){
         paymentRepo.deleteById(id);
         return "DELETED_SUCCESSFULLY";

    }
}
