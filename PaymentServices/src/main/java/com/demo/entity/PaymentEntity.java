package com.demo.entity;


import com.demo.model.PaymentType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT_DB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_Id;
    private String payment_HolderName;
    private Double payment_Amount;
//    @Enumerated
    private String payment_Type;

}
