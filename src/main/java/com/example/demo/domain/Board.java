package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable {
    private String boardNo;
    private String title;
    private String author;
    private String views;
    private Date currentTime;
    private Date modifyTime;
    private String useYN;
}
