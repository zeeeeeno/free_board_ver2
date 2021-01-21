package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Board> loadBoradList(int displayPost, int postNum) throws Exception {
        log.info("BoardServiceImpl - loadBoardList()");

        return boardRepository.loadBoardList(displayPost, postNum);
    }

    @Override
    public Board readDetailBoard(String boardNo) throws Exception {
        log.info("BoardServiceImpl - readDetailBoard() boardNo: " + boardNo);

        return boardRepository.readBoard(boardNo);
    }

    @Override
    public void insertBoard(Board board) throws Exception {
        log.info("BoardServiceImpl - insertBoard() board: " + board);
        boardRepository.insertBoard(board);
    }

    @Override
    public void updateBoard(Board board) throws Exception {
        log.info("BoardServiceImpl - updateBoard() board: " + board);
        boardRepository.updateBoard(board);
    }

    @Override
    public void deleteBoard(String boardNo) throws Exception {
        log.info("BoardServiceImpl - deleteBoard() boardNo: " + boardNo);
        boardRepository.deleteBoard(boardNo);
    }

    @Override
    public void countView(String boardNo) throws Exception {
        log.info("BoardServiceImpl - countView() boardNo: " + boardNo);
        boardRepository.countView(boardNo);
    }

    @Override
    public int boardCount() throws Exception {
        log.info("BoardServiceImpl - boardCount()");

        return boardRepository.boardCount();
    }
}
