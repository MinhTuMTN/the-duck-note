package com.theduck.note.notification.listener;

import static com.theduck.note.commons.constant.MessageQueue.EMAIL_NOTIFICATION;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.theduck.note.commons.event.EmailNoticationEvent;

@Component
public class NotificationListener {

    @RabbitListener(queues = EMAIL_NOTIFICATION)
    public void handleEmailNotification(EmailNoticationEvent event) {
        System.out.println("Received email notification event: " + event);
    }
}
