package com.green.boardauth.appilcation.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInRes {
    private long signedUserId;
    private String nm;
}
