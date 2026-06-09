package com.flea.flea.dto.response;

import com.flea.flea.enumeration.PoolStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PoolResponseOnly {

    public String id;
    public String title;
    public BigDecimal poolAmount;
    public Long minParticipants;
    public Long maxParticipants;
    public String description;
    public PoolStatus status;
    public Long dayPayments;
    public UserResponse poolAdmin;
}
