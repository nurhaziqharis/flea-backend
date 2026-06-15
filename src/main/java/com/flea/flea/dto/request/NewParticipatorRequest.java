package com.flea.flea.dto.request;

import lombok.Getter;

@Getter
public class NewParticipatorRequest {
    private String username;
    private String poolTitle;
    private Boolean isAgree;
    private String poolId;
    private String userId;
}
