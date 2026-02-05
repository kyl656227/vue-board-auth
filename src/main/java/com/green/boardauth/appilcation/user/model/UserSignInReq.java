package com.green.boardauth.appilcation.user.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignInReq {
    private String uid;
    private String upw;
}
