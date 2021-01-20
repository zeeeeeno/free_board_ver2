package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.service.BookService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log
@Controller
@RequestMapping("board")
public class BoardController {

    private ModelAndView modelAndView;
    private Date date;
    private SimpleDateFormat sdf;

    @Autowired
    BookService bookService;

    @GetMapping("list")
    public ModelAndView loadBoardList() throws Exception {
        log.info("BoardController - loadBoardList()");

        modelAndView = new ModelAndView();
        modelAndView.setViewName("board/board_list");
        List<Board> boardArrayList = bookService.loadBoradList();
        log.info(boardArrayList.toString());

        modelAndView.addObject("boardList", boardArrayList);

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
    public String insertBoard(@RequestBody List<Board> board) throws Exception {
        log.info("BoardController - writeBoard() board: " + board.get(0));

        date = new Date();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Board inputBoard = board.get(0);

        inputBoard.setViews("0");
        inputBoard.setUseYN("1");
        inputBoard.setCurrentTime(sdf.format(date));
        inputBoard.setModifyTime(sdf.format(date));

        try {
            bookService.insertBoard(inputBoard);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail!!";
        }

        return "success!!";
    }

    @GetMapping("detail/{boardNo}")
    public ModelAndView readBoard(@PathVariable("boardNo") String boardNo) throws Exception {
        log.info("BoardController - readBoard() boardNo: " + boardNo);

        modelAndView = new ModelAndView();
        modelAndView.setViewName("board/board_detail");

        Board board = bookService.readDetailBoard(boardNo);
        modelAndView.addObject("board", board);

        return modelAndView;
    }
}
