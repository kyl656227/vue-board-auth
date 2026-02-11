package com.green.boardauth.appilcation.board.model;

import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
    public class BoardDetailReq {
        private long id;
        private String title;
        private String contents;
        private String createdAt;
        private String nm;
        private long userId;
    }


