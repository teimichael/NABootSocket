package stu.napls.nabootsocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import stu.napls.nabootsocket.core.dictionary.APIConst;
import stu.napls.nabootsocket.core.dictionary.ConversationConst;
import stu.napls.nabootsocket.core.dictionary.MessageConst;
import stu.napls.nabootsocket.core.exception.Assert;
import stu.napls.nabootsocket.core.response.Response;
import stu.napls.nabootsocket.model.Conversation;
import stu.napls.nabootsocket.model.Message;
import stu.napls.nabootsocket.model.User;
import stu.napls.nabootsocket.service.ConversationService;
import stu.napls.nabootsocket.service.MessageService;
import stu.napls.nabootsocket.service.UserService;
import stu.napls.nabootsocket.util.SimpMessageHeaderAccessorFactory;

import javax.annotation.Resource;

@Controller
public class GroupChatController {

    private static final Logger logger = LoggerFactory.getLogger(GroupChatController.class);

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private UserService userService;

    @Resource
    private MessageService messageService;

    @Resource
    private ConversationService conversationService;

    @MessageMapping("/group/send")
    @SendToUser(APIConst.GROUP_CHANNEL)
    public Response sendGroup(Message message, SimpMessageHeaderAccessor accessor) {
        User sender = userService.findUserBySessionId(accessor.getSessionId());
        Assert.isTrue(sender != null && message.getSender().equals(sender.getUuid()), "Unauthorized channel.");
        Conversation conversation = conversationService.findByUuid(message.getReceiver());
        Assert.notNull(conversation, "Group does not exist.");

        message.setTimestamp(System.currentTimeMillis());

        String[] users = conversation.getUsers().split(ConversationConst.SPLITTER);
        String receiverSessionId;
        for (int i = 0; i < users.length; i++) {
            receiverSessionId = userService.findUserByUuid(users[i]).getSessionId();
            if (receiverSessionId != null && !receiverSessionId.equals(sender.getSessionId())) {
                simpMessagingTemplate.convertAndSendToUser(receiverSessionId, APIConst.GROUP_CHANNEL, Response.success(message), SimpMessageHeaderAccessorFactory.getMessageHeaders(receiverSessionId));
            }
        }
        // TODO Do not judge whether the message was read for now
        message.setReadStatus(MessageConst.READ);

        message.setConversationId(conversation.getId());
        message = messageService.update(message);
        conversation.setLastMessage(message);
        conversationService.update(conversation);

        return Response.success(message);
    }

}
