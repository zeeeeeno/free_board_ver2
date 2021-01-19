package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
