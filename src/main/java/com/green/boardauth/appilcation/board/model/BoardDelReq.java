package com.green.boardauth.appilcation.board.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDelReq {

    private long id;
    private long signedUserId;
}
