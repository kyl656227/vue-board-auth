package com.green.boardauth.appilcation.board;

import com.green.boardauth.appilcation.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {


    int save(BoardPostReq req);


    List<BoardGetRes> findAll(BoardGetReq req);

    int findMaxPage(BoardGetMaxPageReq req);


    BoardDetailReq findContents(long id);



    int update(BoardPutReq req);

    int deleteById(BoardDelReq req);
}
