package com.example.demo.repository;

import com.example.demo.domain.Board;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log
@Repository
public class BoardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createTable() throws Exception {
        log.info("BookRepository - createTable()");
        SingleConnectionDataSource ds = new SingleConnectionDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testdb");
        ds.setUsername("sa");
        ds.setPassword("");

        jdbcTemplate = new JdbcTemplate(ds);

        String query = "create table board" +
                "(" +
                "boardNo integer not null auto_increment," +
                "title varchar(50) not null," +
                "author varchar(20) not null," +
                "contents varchar(1000) not null," +
                "views integer not null," +
                "currentTime date," +
                "modifyTime date," +
                "useYN varchar(10) not null," +
                "primary key(boardNo)" +
                ");";

        jdbcTemplate.execute(query);
    }

    public List<Board> loadBoardList() throws Exception{
        log.info("BookRepository - loadBoardList()");

        List<Board> results = jdbcTemplate.query(
                "select * from board",
                new RowMapper<Board>() {
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();
                        board.setBoardNo(rs.getString("boardNo"));
                        board.setTitle(rs.getString("title"));
                        board.setAuthor(rs.getString("author"));
                        board.setContents(rs.getString("contents"));
                        board.setViews(rs.getString("views"));
                        board.setCurrentTime(rs.getString("currentTime"));
                        board.setModifyTime(rs.getString("modifyTime"));
                        board.setUseYN(rs.getString("useYN"));
                        return board;
                    }
                }
        );
        return results;
    }

    public void insertBoard(Board board) throws Exception {
        log.info("BookRepository - insertBoard() board: " + board);

        String query = "insert into board (title, author, contents, views, currentTime, modifyTime, useYN) values (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {
                        PreparedStatement ps = con.prepareStatement(query, new String[] {"boardNo"});
                        ps.setString(1, board.getTitle());
                        ps.setString(2, board.getAuthor());
                        ps.setString(3, board.getContents());
                        ps.setString(4, board.getViews());
                        ps.setString(5, board.getCurrentTime());
                        ps.setString(6, board.getModifyTime());
                        ps.setString(7, board.getUseYN());

                        return ps;
                    }
                }, keyHolder);

        board.setBoardNo(keyHolder.getKey().toString());
    }

    public Board readBoard(String boardNo) throws Exception{
        log.info("BookRepository - readBoard() boardNo: " + boardNo);
        List<Board> results = jdbcTemplate.query(
                "select boardNo, title, author, contents, views, currentTime, modifyTime, useYN " +
                        "from board where boardNo = ?",
                new RowMapper<Board>() {
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Board board = new Board();

                        board.setBoardNo(rs.getString("boardNo"));
                        board.setTitle(rs.getString("title"));
                        board.setAuthor(rs.getString("author"));
                        board.setContents(rs.getString("contents"));
                        board.setViews(rs.getString("views"));
                        board.setCurrentTime(rs.getString("currentTime"));
                        board.setModifyTime(rs.getString("modifyTime"));
                        board.setUseYN(rs.getString("useYN"));

                        System.out.println("BoardRepository: " + board);

                        return board;
                    }
                }, boardNo
        );

        return results.isEmpty() ? null : results.get(0);
    }
}
