package com.example.demo.service;

import com.example.demo.domain.MessageDto;
import org.springframework.messaging.Message;

public interface MessageService {
    /**
     * SQS 내에 있는 문자열 타입 메시지 받기
     */
    Message<String> receiveTextMessage();

    /**
     * SQS 내에 있는 객체 타입 메시지 받기
     */
    MessageDto receiveObjectMessage();

    /**
     * SQS 에 문자열 타입 메시지 보내
     * @param message: 보낼 메시지
     */
    void sendMessage(String message);

    /**
     * SQS 에 객체 타입 메시지 보내
     * @param message: 보낼 메시지
     */
    void sendMessage(MessageDto message);
}
