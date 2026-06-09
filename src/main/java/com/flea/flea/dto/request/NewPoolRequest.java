package com.flea.flea.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class NewPoolRequest {

    public String title;
    public BigDecimal poolAmount;
    public Long minParticipants;
    public Long maxParticipants;
    public String description;
    public Long dayPayments;

}
