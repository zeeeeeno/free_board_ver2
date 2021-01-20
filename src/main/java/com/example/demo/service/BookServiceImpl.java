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
public class BookServiceImpl implements BookService {
    @Autowired
    BoardRepository bookRepository;

    @Override
    public List<Board> loadBoradList() throws Exception {
        log.info("BookServiceImpl - loadBoardList()");

        return bookRepository.loadBoardList();
    }

    @Override
    public Board readDetailBoard(String boardNo) throws Exception {
        log.info("BookServiceImpl - readDetailBoard()");

        return bookRepository.readBoard(boardNo);
    }

    @Override
    public void insertBoard(Board board) throws Exception {
        log.info("BookServiceImpl - insertBoard() board: " + board);
        bookRepository.insertBoard(board);
    }

    @Override
    public void updateBoard(Board board) throws Exception {

    }

    @Override
    public void deleteBorad(String boradNo) throws Exception {

    }
}
