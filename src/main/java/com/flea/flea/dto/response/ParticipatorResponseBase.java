package com.flea.flea.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipatorResponseBase {
    private String username;
    private String poolTitle;
    private Boolean isAgree;
    private Integer turn;
    private PoolResponseOnly participatorPool;
    private UserResponseOnly participatorUser;
}

