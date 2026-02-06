package com.green.boardauth.appilcation.board;

import com.green.boardauth.appilcation.board.model.BoardPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    int getpost(BoardPostReq req);

}
