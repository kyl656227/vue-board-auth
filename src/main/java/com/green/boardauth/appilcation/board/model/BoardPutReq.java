package com.green.boardauth.appilcation.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardPutReq {
    private String title;
    private String contents;

}
