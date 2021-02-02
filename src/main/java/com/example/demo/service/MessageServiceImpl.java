package com.example.demo.service;

import lombok.extern.java.Log;
import com.example.demo.domain.MessageDto;
import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;

@Log
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    public MessageServiceImpl(AmazonSQS amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
    }

    /**
     * Message Convertor
     */
    private final QueueMessagingTemplate queueMessagingTemplate;


    /**
     * 문자열 타입의 메시지 송신 예제
     * @param message: text
     */
    public void sendMessage(String message) {
        log.info("MessageService - sendMessage() message: " + message);

        Message<String> newMessage = MessageBuilder.withPayload(message).build();
        queueMessagingTemplate.send("paul-standard-queue", newMessage);
    }

    /**
     * 문자열 타입의 메시지 수신 예제
     */
    public Message<String> receiveTextMessage() {
        log.info("MessageService - receiveTextMessage()");

        return (Message<String>) queueMessagingTemplate.receive("paul-standard-queue");
    }

    /**
     * 객체 타입의 메시지 송신 예제
     * @param message: object
     */
    public void sendMessage(MessageDto message) {
        log.info("MessageService - sendMessage() message: " + message);

        queueMessagingTemplate.convertAndSend("paul-standard-queue", message);
    }

    /**
     * 객체 타입의 메시지 수신 예제
     */
    public MessageDto receiveObjectMessage() throws NullPointerException{
        log.info("MessageService - receiveObjectMessage()");
        return queueMessagingTemplate.receiveAndConvert("paul-standard-queue", MessageDto.class);
    }
}
