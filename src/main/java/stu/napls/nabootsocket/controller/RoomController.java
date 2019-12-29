package stu.napls.nabootsocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import stu.napls.nabootsocket.model.Message;

@Controller
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @MessageMapping("/room")
    @SendTo("/from/room")
    public Message room(Message message) {
        logger.info(message.getContent());
        return message;
    }
}
