package com.green.boardauth.appilcation.board;


import com.green.boardauth.appilcation.board.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public long postBoard(BoardPostReq req) {
        boardMapper.save(req);
        return req.getId();
    }

    public List<BoardGetRes> getBoardList(BoardGetReq req){
        return boardMapper.findAll(req);
    }

    public int getBoardMaxPage(BoardGetMaxPageReq req) {
        return boardMapper.findMaxPage(req);}


    public BoardDetailReq getBoardContents(long id) {
        return boardMapper.findContents(id);
    }

    public int delBoard(BoardDelReq req) {
        return boardMapper.deleteById(req);
    }

    public int putBoard(BoardPutReq req){
        return boardMapper.update(req);
    }
}
