package com.green.boardauth.appilcation.board.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Setter
@Getter
public class GetTextRes {
    private String searchText;

    public GetTextRes(@BindParam("search_text") String searchText) {
        this.searchText = searchText;
    }
}
