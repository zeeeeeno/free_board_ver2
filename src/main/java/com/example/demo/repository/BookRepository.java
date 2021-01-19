package com.example.demo.repository;

import com.example.demo.domain.Board;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Board> loadBoardList() {
        String query = "select * from board";
        List<Board> results = jdbcTemplate.query(
                "select board_no, title, content, writer, reg_date from cafe_board " +
                        "where board_no > 0 order by board_no desc, reg_date desc",
                new RowMapper<Board>() {
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();
//                        board.setBoardNo(rs.getInt("board_no"));
//                        board.setTitle(rs.getString("title"));
//                        board.setContent(rs.getString("content"));
//                        board.setWriter(rs.getString("writer"));
//                        board.setRegDate(rs.getDate("reg_date"));
                        return board;
                    }
                }
        );
        return null;
    }
}
