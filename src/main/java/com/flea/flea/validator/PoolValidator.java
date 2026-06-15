package com.flea.flea.validator;

import com.flea.flea.domain.entity.Pool;
import com.flea.flea.domain.entity.User;
import com.flea.flea.dto.request.NewPoolRequest;
import com.flea.flea.enumeration.PoolStatus;
import com.flea.flea.implementation.CommonAction;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PoolValidator {

    private CommonAction commonAction;

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

    public void deletePoolValidator(Pool pool){
        if(pool.getStatus() != PoolStatus.RECRUITING){
            throw new ValidationException("This pool cannot be deleted");
        }

        User currentUser = commonAction.getCurrentUser();
        User poolAdmin = pool.getPoolAdmin();
        if(currentUser != poolAdmin){
            throw new ValidationException("You are not admin of this pool");
        }
    }

}
