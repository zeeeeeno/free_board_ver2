package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * create table board
 * (
 * boardNo integer not null auto_increment,
 * title varchar(50) not null,
 * author varchar(20) not null,
 * contents varchar(1000) not null,
 * views integer not null,
 * currentTime date,
 * modifyTime date,
 * useYN varchar(10) not null,
 * primary key(boardNo)
 * );
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable {
    private String boardNo;
    private String title;
    private String author;
    private String contents;
    private String views;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateTime;
    private String useYN;
}
