package com.demo.department.CustomException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralMessageException extends  RuntimeException{

    private String errorCodes;
    private String errorMessages;

    public GeneralMessageException(String errorCodes,String errorMessages){
        super(String.format("%s : %s",errorCodes,errorMessages));
        this.errorCodes=errorCodes;
        this.errorMessages=errorMessages;
    }
}
