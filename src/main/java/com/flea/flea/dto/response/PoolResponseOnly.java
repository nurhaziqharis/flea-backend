package com.flea.flea.dto.response;

import com.flea.flea.enumeration.PoolStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public class PoolResponseOnly {

    public UUID id;
    public String title;
    public BigDecimal poolAmount;
    public Long minParticipants;
    public Long maxParticipants;
    public String description;
    public PoolStatus status;
    public Long dayPayments;
}
