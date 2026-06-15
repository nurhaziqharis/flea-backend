package com.flea.flea.mapper;

import com.flea.flea.domain.entity.Participator;
import com.flea.flea.dto.response.ParticipatorResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipatorMapper {

    private final PoolMapper poolMapper;
    private final UserMapper userMapper;

    public ParticipatorResponseBase toParticipatorResponseBase(Participator participator) {
        ParticipatorResponseBase response = new ParticipatorResponseBase();
        response.setUsername(participator.getUsername());
        response.setPoolTitle(participator.getPoolTitle());
        response.setIsAgree(participator.getIsAgree());
        response.setTurn(participator.getTurn());
        response.setParticipatorPool(participator.getParticipatorPool() == null
                ? null
                : poolMapper.toPoolResponseOnly(participator.getParticipatorPool()));
        response.setParticipatorUser(participator.getParticipatorUser() == null
                ? null
                : userMapper.toUserResponseOnly(participator.getParticipatorUser()));

        return response;
    }
}
