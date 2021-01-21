package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Page;
import com.example.demo.service.BoardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log
@Controller
@RequestMapping("board")
public class BoardController {

    private ModelAndView modelAndView;
    private Date date;
    private SimpleDateFormat sdf;
    private Board board;

    @Autowired
    BoardService bookService;

    @GetMapping("list")
    public ModelAndView loadBoardList(HttpServletRequest request) throws Exception {
        log.info("BoardController - loadBoardList()");

        modelAndView = new ModelAndView();
        int num = 1;

        try {
            num = Integer.parseInt(request.getParameter("num"));
        } catch (Exception e) {
            log.info("num값 미지정");
        }

        Page page = new Page();
        page.setNum(num);
        int boardSize = bookService.boardCount();
        page.setCount(boardSize);

        List<Board> boardArrayList = bookService.loadBoradList(page.getDisplayPost(), page.getPostNum());
        modelAndView.addObject("boardList", boardArrayList);
        modelAndView.addObject("select", num);
        modelAndView.addObject("page", page);
        modelAndView.addObject("boardSize", boardSize);

        modelAndView.setViewName("board/board_list");

        return modelAndView;
    }

    @GetMapping("write")
    public ModelAndView writeBoard() {
        log.info("BoardController - writeBoard()");

        modelAndView = new ModelAndView();
        modelAndView.setViewName("board/board_write");

        return modelAndView;
    }

    @PostMapping("insert")
    @ResponseBody
    public String insertBoard(@RequestBody List<Board> boardParam) throws Exception {
        log.info("BoardController - writeBoard() board: " + boardParam.get(0));

        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        board = boardParam.get(0);

        board.setViews("0");
        board.setUseYN("1");
        board.setCurrentTime(sdf.format(date));
        board.setModifyTime(sdf.format(date));

        try {
            bookService.insertBoard(board);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail!!";
        }

        return "success!!";
    }

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

    @PostMapping("update")
    @ResponseBody
    public String updateBoard(@RequestBody List<Board> board) throws Exception {
        log.info("BoardController - updateBoard() board: " + board.get(0));

        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Board updateBoard = board.get(0);

        updateBoard.setModifyTime(sdf.format(date));

        try {
            bookService.updateBoard(updateBoard);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail!!";
        }

        return "success!!";
    }

    @PostMapping("delete")
    @ResponseBody
    public String deleteBoard(@RequestBody String boardNo) throws Exception {
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
