package com.example.demo.repository;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.extern.java.Log;
import java.sql.PreparedStatement;
import com.example.demo.domain.Board;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.beans.factory.annotation.Autowired;


@Log
@Repository
@SuppressWarnings("ALL")
public class BoardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 총 게시글 수 계산
     * @return: 총 테이블 게시글 수 - 삭제 된 게시글 수
     * @throws Exception: 에러 체크
     */
    public int boardCount() throws Exception {
        log.info("BoardRepository - boardCount()");
        int size = 0;

        try {
            size = jdbcTemplate.queryForObject("SELECT count(boardNo) FROM board WHERE useYN = 1", Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }

    /**
     * 게시글 리스트 가져오기
     * @param displayPost
     * @param postNum
     * @return
     * @throws Exception
     */
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
                        board.setCreateTime(rs.getDate("createTime"));
                        board.setUpdateTime(rs.getDate("updateTime"));
                        board.setUseYN(rs.getString("useYN"));
                        return board;
                    }
                }
        );
        return results;
    }

    /**
     * 게시글 등록
     * @param board: 등록할 게시글
     * @throws Exception: 오류 체크
     */
    public void insertBoard(Board board) throws Exception {
        log.info("BoardRepository - insertBoard() board: " + board);

        String query = "INSERT INTO board (title, author, contents) VALUES (?, ?, ?)";

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

                        return ps;
                    }
                }, keyHolder);

        board.setBoardNo(keyHolder.getKey().toString());
    }

    /**
     * 게시글 상세 내용 가져오기
     * @param boardNo: 게시글 번호
     * @return 게시글
     * @throws Exception: 오류 체크
     */
    public Board readBoard(String boardNo) throws Exception {
        log.info("BoardRepository - readBoard() boardNo: " + boardNo);
        List<Board> results = jdbcTemplate.query(
                "SELECT boardNo, title, author, contents, views, createTime, updateTime, useYN " +
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
                        board.setCreateTime(rs.getDate("createTime"));
                        board.setUpdateTime(rs.getDate("updateTime"));
                        board.setUseYN(rs.getString("useYN"));

                        return board;
                    }
                }, boardNo
        );

        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * 게시글 조회수 가져오기
     * @param boardNo: 게시글 번호
     * @throws Exception: 오류 체크
     */
    public void countView(String boardNo) throws Exception {
        log.info("BoardRepository - countView() boardNo: " + boardNo);

        String query = "UPDATE board SET views = views + 1 WHERE boardNo = ?";
        jdbcTemplate.update(query, boardNo);
    }

    /**
     * 게시글 수정
     * @param board: 수정할 게시글
     * @throws Exception: 오류체크
     */
    public void updateBoard(Board board) throws Exception {
        log.info("BoardRepository - updateBoard() board: " + board);

        String query = "UPDATE board SET contents = ? WHERE boardNo = ?";
        jdbcTemplate.update(query, board.getContents(), board.getBoardNo());
    }

    /**
     * 게시글 삭제
     * @param boardNo: 게시글 번호
     * @throws Exception: 오류 체크
     */
    public void deleteBoard(String boardNo) throws Exception {
        log.info("BoardRepository - deleteBoard() boardNo: " + boardNo);

        String query = "UPDATE board SET useYN = 0 WHERE boardNo = ?";
        jdbcTemplate.update(query, boardNo);
    }
}
