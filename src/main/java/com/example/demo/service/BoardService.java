package com.example.demo.service;

import com.example.demo.domain.Board;

import java.util.List;

public interface BoardService {
    public List<Board> loadBoradList(int displayPost, int postNum) throws Exception;
    public Board readDetailBoard(String boardNo) throws Exception;
    public void insertBoard(Board board) throws Exception;
    public void updateBoard(Board board) throws Exception;
    public void deleteBoard(String boradNo) throws Exception;
    public void countView(String boardNo) throws Exception;
    public int boardCount() throws Exception;
}
