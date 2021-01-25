package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

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
    private String currentTime;
    private String modifyTime;
    private String useYN;
}
