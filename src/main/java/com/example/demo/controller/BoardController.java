package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import lombok.extern.java.Log;
import javax.servlet.http.Cookie;
import com.example.demo.domain.Page;
import com.example.demo.domain.Board;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.service.BoardService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Log
@Controller
@RequestMapping("board")
public class BoardController {

    private Board board;
    private ModelAndView modelAndView;
    private final BoardService bookService;

    @Autowired
    public BoardController(BoardService bookService) {
        this.bookService = bookService;
    }

    /**
     * 게시글 가져오기
     * @return 게시글 목록 페이지
     */
    @GetMapping("list")
    public ModelAndView loadBoardList(@RequestParam(value = "num", defaultValue = "1") int numParam) {
        log.info("BoardController - loadBoardList()");

        modelAndView = new ModelAndView();
        Page page = new Page();
        int boardSize = 0;

        try {
            page.setNum(numParam);
            boardSize = bookService.boardCount();
            page.setCount(boardSize);
            List<Board> boardArrayList = bookService.loadBoardList(page.getDisplayPost(), page.getPostNum());
            modelAndView.addObject("boardList", boardArrayList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        modelAndView.addObject("select", numParam);
        modelAndView.addObject("page", page);
        modelAndView.addObject("boardSize", boardSize);

        modelAndView.setViewName("board/board_list");

        return modelAndView;
    }

    /**
     * 게시글 작성
     * @return 게시글 작성 페이지
     */
    @GetMapping("write")
    public ModelAndView writeBoard() {
        log.info("BoardController - writeBoard()");

        modelAndView = new ModelAndView();
        modelAndView.setViewName("board/board_write");

        return modelAndView;
    }

    /**
     * 게시글 작성
     * @param boardList: 게시글
     * @return 게시글 삽입 결과
     */
    @ResponseBody
    @PostMapping("insert")
    public String insertBoard(@RequestBody List<Board> boardList) {
        log.info("BoardController - writeBoard() board: " + boardList.get(0));

        try {
            bookService.insertBoard(boardList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            return "fail!!";
        }

        return "success!!";
    }

    /**
     * 게시글 상세 내용 출력
     * @param boardNo: 게시글 번호
     * @param request: request
     * @param response: response
     * @return 게시글 상세 내용 페이지
     * @throws Exception: 오류 체크
     */
    @GetMapping("detail/{boardNo}")
    public ModelAndView readBoard(@PathVariable("boardNo") String boardNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("BoardController - readBoard() boardNo: " + boardNo);

        Cookie[] cookies = request.getCookies();
        Cookie vCookie = null;

        modelAndView = new ModelAndView();

        if (cookies != null && cookies.length > 0) {
            log.info("cookies != null && cookies.length > 0");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookie" + boardNo)) {
                    log.info("cookie.getName().equals(cookie + boardNo)");
                    vCookie = cookie;
                }
            }
        }

        if (vCookie == null) {
            log.info("vCookie == null");

            Cookie newCookie = new Cookie("cookie" + boardNo, "|" + boardNo + "|");
            response.addCookie(newCookie);
            bookService.countView(boardNo);
        }

        board = bookService.readDetailBoard(boardNo);
        modelAndView.addObject("board", board);

        modelAndView.setViewName("board/board_detail");

        return modelAndView;
    }

    /**
     * 게시글 수정
     * @param board: 게시글
     * @return 게시글 수정 여부
     */
    @ResponseBody
    @PostMapping("update")
    public String updateBoard(@RequestBody List<Board> board) {
        log.info("BoardController - updateBoard() board: " + board.get(0));

        Board updateBoard = board.get(0);

        try {
            bookService.updateBoard(updateBoard);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail!!";
        }

        return "success!!";
    }

    /**
     * 게시글 삭제
     * @param boardNo: 게시글 번호
     * @return 게시글 삭제 여부
     */
    @ResponseBody
    @PostMapping("delete")
    public String deleteBoard(@RequestBody String boardNo) {
        log.info("BoardController - deleteBoard() boardNo: " + boardNo);

        try {
            bookService.deleteBoard(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail!!";
        }

        return "success!!";
    }
}
