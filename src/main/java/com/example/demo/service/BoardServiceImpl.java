package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    BoardRepository boardRepository;

    /**
     * 게시판 리스트 가져오기
     * @param displayPost: 보여질 게시판 수
     * @param postNum: 페이지 수
     * @return 게시판 리스트
     * @throws Exception: 오류 체크
     */
    @Override
    public List<Board> loadBoardList(int displayPost, int postNum) throws Exception {
        log.info("BoardServiceImpl - loadBoardList()");

        return boardRepository.loadBoardList(displayPost, postNum);
    }

    /**
     * 게시글 상세 내용 가져오기
     * @param boardNo: 게시글 번호
     * @return 게시글
     * @throws Exception: 오류 체크
     */
    @Override
    public Board readDetailBoard(String boardNo) throws Exception {
        log.info("BoardServiceImpl - readDetailBoard() boardNo: " + boardNo);

        return boardRepository.readBoard(boardNo);
    }

    /**
     * 게시글 등록
     * @param board: 등록할 게시글
     * @throws Exception: 오류 체크
     */
    @Override
    public void insertBoard(Board board) throws Exception {
        log.info("BoardServiceImpl - insertBoard() board: " + board);
        boardRepository.insertBoard(board);
    }

    /**
     * 게시글 수정
     * @param board: 수정할 게시글
     * @throws Exception: 오류체크
     */
    @Override
    public void updateBoard(Board board) throws Exception {
        log.info("BoardServiceImpl - updateBoard() board: " + board);
        boardRepository.updateBoard(board);
    }

    /**
     * 게시글 삭제
     * @param boardNo: 게시글 번호
     * @throws Exception: 오류 체크
     */
    @Override
    public void deleteBoard(String boardNo) throws Exception {
        log.info("BoardServiceImpl - deleteBoard() boardNo: " + boardNo);
        boardRepository.deleteBoard(boardNo);
    }

    /**
     * 게시글 조회수 가져오기
     * @param boardNo: 게시글 번호
     * @throws Exception: 오류 체크
     */
    @Override
    public void countView(String boardNo) throws Exception {
        log.info("BoardServiceImpl - countView() boardNo: " + boardNo);
        boardRepository.countView(boardNo);
    }

    /**
     * 총 게시글 수 가져오기
     * @return 총 게시글 수
     * @throws Exception: 오류 체크
     */
    @Override
    public int boardCount() throws Exception {
        log.info("BoardServiceImpl - boardCount()");

        return boardRepository.boardCount();
    }
}
