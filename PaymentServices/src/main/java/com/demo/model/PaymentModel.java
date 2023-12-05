package com.demo.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {

    private String payment_HolderName;
    private Double payment_Amount;
//    @Enumerated
    private String payment_Type;
}
