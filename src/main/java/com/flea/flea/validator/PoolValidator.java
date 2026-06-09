package com.flea.flea.validator;

import com.flea.flea.dto.request.NewPoolRequest;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PoolValidator {

    public void newPoolValidator(NewPoolRequest newPoolRequest){
        String errorMessage = "Not valid: ";
        Boolean isValid = true;
        if(newPoolRequest.getTitle().isBlank()){
            isValid = false;
            errorMessage = errorMessage + "Title cannot be blank | ";
        }
        if(newPoolRequest.getDayPayments() < 1 || newPoolRequest.getDayPayments() > 28){
            isValid = false;
            errorMessage = errorMessage + "Day payment must be range 1 - 28 |";
        }
        if(!isValid){
            throw new ValidationException(errorMessage);
        }
    }

}
