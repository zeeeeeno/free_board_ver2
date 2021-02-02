package com.example.demo.listener;

import lombok.extern.java.Log;
import com.example.demo.domain.MessageDto;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;

@Log
@Component
public class MessageListener {
    /**
     * 1. value = SQS Name
     * 2. deletionPolicy: 메시지를 받은 이후의 삭제 정책
     *  1) ALWAYS: 메소드로 메시지가 들어오면 무조건 삭제 요청을 보낸다.
     *  2) NEVER: 절대 삭제 요청을 보내지 않는다. ACKnowledgement 파라미터를 바인딩 받아 ack 메소드를 호출할 때 삭제 요청을 보낸다.
     *  3) NO_REDRIVE: redrive policy(DLO)가 정의되지 않으면 메시지를 삭제한다.
     *  4) ON_SUCCESS: SqsListener 어노테이션이 붙은 메소드에서 에러가 나지 않으면 메시지를 삭제한다.
     */
    @SqsListener(value = "paul-standard-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload MessageDto message, Acknowledgment ack) {
        log.info("MessageListener - listen(): " + message);
        ack.acknowledge();
    }
}
