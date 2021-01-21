package com.example.demo;

import com.example.demo.repository.BoardRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws Exception {
        System.out.println("init()");
        BoardRepository boardRepository = new BoardRepository();
        boardRepository.createTable();
        boardRepository.initTuple();
    }
}
