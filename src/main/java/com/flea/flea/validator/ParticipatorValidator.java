package com.flea.flea.validator;

import com.flea.flea.domain.repository.PoolRepository;
import com.flea.flea.domain.repository.UserRepository;
import com.flea.flea.dto.request.NewParticipatorRequest;
import com.flea.flea.implementation.CommonAction;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ParticipatorValidator {

    private final UserRepository userRepository;
    private final PoolRepository poolRepository;
    private final CommonAction commonAction;

    public void newParticipatorValidator(NewParticipatorRequest newParticipatorRequest){
        String errorMessage = "Error: ";
        Boolean isValid = true;
        if(newParticipatorRequest.getUserId().isBlank()){
            isValid = false;
            errorMessage = errorMessage + "User ID cannot be blank | ";
        }

        if(newParticipatorRequest.getPoolId().isBlank()){
            isValid = false;
            errorMessage = errorMessage + "Pool ID cannot be blank | ";
        }

        if(newParticipatorRequest.getPoolTitle().isBlank()){
            isValid = false;
            errorMessage = errorMessage + "Pool title cannot be blank | ";
        }

        if(newParticipatorRequest.getIsAgree() == null || !newParticipatorRequest.getIsAgree()){
            isValid = false;
            errorMessage = errorMessage + "Participator must agree to the terms";
        }

        if(!isValid){
            throw new ValidationException(errorMessage);
        }
    }

}
