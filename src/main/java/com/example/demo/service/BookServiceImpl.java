package com.example.demo.service;

import com.example.demo.domain.Board;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Log
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Override
    public ArrayList<Board> loadBoradList() throws Exception {
        log.info("BookServiceImpl - loadBoradList()");

        return null;
    }

    @Override
    public Board readDetailBoard() throws Exception {
        return null;
    }

    @Override
    public void insertBorad(Board board) throws Exception {

    }

    @Override
    public void updateBoard(String contents) throws Exception {

    }

    @Override
    public void deleteBorad(String boradNo) throws Exception {

    }
}
