package com.example.demo.service;

import com.example.demo.domain.Board;

import java.util.ArrayList;

public interface BookService {
    public ArrayList<Board> loadBoradList() throws Exception;
    public Board readDetailBoard() throws Exception;
    public void insertBorad(Board board) throws Exception;
    public void updateBoard(String contents) throws Exception;
    public void deleteBorad(String boradNo) throws Exception;
}
