package stu.napls.nabootsocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import stu.napls.nabootsocket.core.exception.Assert;
import stu.napls.nabootsocket.core.response.Response;
import stu.napls.nabootsocket.model.Message;
import stu.napls.nabootsocket.model.User;
import stu.napls.nabootsocket.service.UserService;

import javax.annotation.Resource;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private UserService userService;

    @MessageMapping("/chat/send")
    @SendToUser("/single/message")
    public Response send(Message message, SimpMessageHeaderAccessor accessor) {
        User sender = userService.findUserBySessionId(accessor.getSessionId());
        Assert.isTrue(sender != null && message.getSender().equals(sender.getUuid()), "Unauthorized channel.");
        User receiver = userService.findUserByUuid(message.getReceiver());
        Assert.notNull(receiver, "Receiver does not exist.");

        // TODO message persistence
        message.setTimestamp(System.currentTimeMillis());


        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
                .create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(receiver.getSessionId());
        headerAccessor.setLeaveMutable(true);

        if (receiver.getSessionId() != null) {
            // Receiver is online
            simpMessagingTemplate.convertAndSendToUser(receiver.getSessionId(), "/single/message", Response.success(message), headerAccessor.getMessageHeaders());
        } else {
            // Receiver is offline

        }

        return Response.success(message);
    }

//    @PostMapping("/post")
//    public Response post(@RequestBody Message message) {
//        System.out.println(message.getContent());
//        User user = userService.findUserByUuid("user1");
//        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
//                .create(SimpMessageType.MESSAGE);
//        headerAccessor.setSessionId(user.getSessionId());
//        headerAccessor.setLeaveMutable(true);
//        simpMessagingTemplate.convertAndSendToUser(user.getSessionId(), "/single/message", message, headerAccessor.getMessageHeaders());
//        return Response.success("ok");
//    }

}
