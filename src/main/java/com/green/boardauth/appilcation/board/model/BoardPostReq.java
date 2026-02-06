package com.green.boardauth.appilcation.board.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardPostReq {
    private String title;
    private String contents;
    private long userid;
}
