package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.Page;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log
@Repository
public class BoardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createTable() throws Exception {
        log.info("BoardRepository - createTable()");
        SingleConnectionDataSource ds = new SingleConnectionDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testdb");
        ds.setUsername("sa");
        ds.setPassword("");

        jdbcTemplate = new JdbcTemplate(ds);

        String query = "CREATE TABLE board" +
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

    public void initTuple() throws Exception {
        log.info("BoardRepository - initTuple()");
        String query = "";

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int idx = 1; idx < 21; idx++) {
            query = "INSERT INTO board (title, author, contents, views, currentTime, modifyTime, useYN) VALUES (?, ?, ?, ?, ?, ?, ?);";
            String title = "title" + idx;
            String author = "author" + idx;
            String contents = "contents" + idx;
            int views = 0;
            String currentTime = sdf.format(date);
            String modifyTime = sdf.format(date);
            String useYN = "1";

            jdbcTemplate.update(query, title, author, contents, views, currentTime, modifyTime, useYN);
        }
    }

    public int boardCount() throws Exception {
        log.info("BoardRepository - boardCount()");

        int size = jdbcTemplate.queryForObject("SELECT count(boardNo) FROM board WHERE useYN = 1", Integer.class);

        return size;
    }

    public List<Board> loadBoardList(int displayPost, int postNum) throws Exception {
        log.info("BoardRepository - loadBoardList()");

        String query = "SELECT * FROM board WHERE useYN = 1 ORDER BY boardNo DESC LIMIT " + displayPost + ", " + postNum + ";";

        List<Board> results = jdbcTemplate.query(
                query,
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
        log.info("BoardRepository - insertBoard() board: " + board);

        String query = "INSERT INTO board (title, author, contents, views, currentTime, modifyTime, useYN) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {
                        PreparedStatement ps = con.prepareStatement(query, new String[]{"boardNo"});
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

    public Board readBoard(String boardNo) throws Exception {
        log.info("BoardRepository - readBoard() boardNo: " + boardNo);
        List<Board> results = jdbcTemplate.query(
                "SELECT boardNo, title, author, contents, views, currentTime, modifyTime, useYN " +
                        "FROM board WHERE boardNo = ?",
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

                        return board;
                    }
                }, boardNo
        );

        return results.isEmpty() ? null : results.get(0);
    }

    public void countView(String boardNo) throws Exception {
        log.info("BoardRepository - countView() boardNo: " + boardNo);

        String query = "UPDATE board SET views = views + 1 WHERE boardNo = ?";
        jdbcTemplate.update(query, boardNo);
    }

    public void updateBoard(Board board) throws Exception {
        log.info("BoardRepository - updateBoard() board: " + board);

        String query = "UPDATE board SET contents = ?, MODIFYTIME = ? WHERE boardNo = ?";
        jdbcTemplate.update(query, board.getContents(), board.getModifyTime(), board.getBoardNo());
    }

    public void deleteBoard(String boardNo) throws Exception {
        log.info("BoardRepository - deleteBoard() boardNo: " + boardNo);

        String query = "UPDATE board SET useYN = 0 WHERE boardNo = ?";
        jdbcTemplate.update(query, boardNo);
    }
}
