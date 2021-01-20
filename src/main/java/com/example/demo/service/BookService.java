package com.example.demo.service;

import com.example.demo.domain.Board;

import java.util.List;

public interface BookService {
    public List<Board> loadBoradList() throws Exception;
    public Board readDetailBoard(String boardNo) throws Exception;
    public void insertBoard(Board board) throws Exception;
    public void updateBoard(Board board) throws Exception;
    public void deleteBorad(String boradNo) throws Exception;
}
