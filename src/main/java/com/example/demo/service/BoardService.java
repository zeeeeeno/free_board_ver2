package com.example.demo.service;

import java.util.List;
import com.example.demo.domain.Board;

public interface BoardService {
    /**
     * 게시판 리스트 가져오기
     * @param displayPost: 보여질 게시판 수
     * @param postNum: 페이지 수
     * @return 게시판 리스트
     * @throws Exception: 오류 체크
     */
    List<Board> loadBoardList(int displayPost, int postNum) throws Exception;

    /**
     * 게시글 상세 내용 가져오기
     * @param boardNo: 게시글 번호
     * @return 게시글
     * @throws Exception: 오류 체크
     */
    Board readDetailBoard(String boardNo) throws Exception;

    /**
     * 게시글 등록
     * @param board: 등록할 게시글
     * @throws Exception: 오류 체크
     */
    void insertBoard(Board board) throws Exception;

    /**
     * 게시글 수정
     * @param board: 수정할 게시글
     * @throws Exception: 오류체크
     */
    void updateBoard(Board board) throws Exception;

    /**
     * 게시글 삭제
     * @param boardNo: 게시글 번호
     * @throws Exception: 오류 체크
     */
    void deleteBoard(String boardNo) throws Exception;

    /**
     * 게시글 조회수 가져오기
     * @param boardNo: 게시글 번호
     * @throws Exception: 오류 체크
     */
    void countView(String boardNo) throws Exception;

    /**
     * 총 게시글 수 가져오기
     * @return 총 게시글 수
     * @throws Exception: 오류 체크
     */
    int boardCount() throws Exception;
}
