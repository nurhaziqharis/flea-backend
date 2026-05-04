package com.flea.flea.mapper;

import com.flea.flea.domain.entity.Pool;
import com.flea.flea.dto.response.PoolResponseOnly;
import org.springframework.stereotype.Component;

@Component
public class PoolMapper {

    public PoolResponseOnly toPoolResponseOnly(Pool pool) {
        return PoolResponseOnly.builder()
                .id(pool.getId())
                .title(pool.getTitle())
                .poolAmount(pool.getPoolAmount())
                .minParticipants(pool.getMinParticipants())
                .maxParticipants(pool.getMaxParticipants())
                .description(pool.getDescription())
                .dayPayments(pool.getDayPayments())
                .status(pool.getStatus())
                .build();
    }

}
