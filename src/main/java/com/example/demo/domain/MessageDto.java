package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/*
@Getter - 직렬화 할 때 필요
@NoArgsConstructor - 역직렬화할 때 필요
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String sender;
    private String contents;
    private String date;
}
