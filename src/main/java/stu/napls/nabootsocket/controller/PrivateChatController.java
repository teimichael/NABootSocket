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
public class PrivateChatController {

    private static final Logger logger = LoggerFactory.getLogger(PrivateChatController.class);

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private UserService userService;

    @Resource
    private MessageService messageService;

    @Resource
    private ConversationService conversationService;

    @MessageMapping("/private/send")
    @SendToUser(APIConst.PRIVATE_CHANNEL)
    public Response sendPrivate(Message message, SimpMessageHeaderAccessor accessor) {
        User sender = userService.findUserBySessionId(accessor.getSessionId());
        Assert.isTrue(sender != null && message.getSender().equals(sender.getUuid()), "Unauthorized channel.");
        User receiver = userService.findUserByUuid(message.getReceiver());
        Assert.notNull(receiver, "Receiver does not exist.");

        message.setTimestamp(System.currentTimeMillis());
        if (receiver.getSessionId() != null) {
            // Receiver is online
            message.setReadStatus(MessageConst.READ);
            simpMessagingTemplate.convertAndSendToUser(receiver.getSessionId(), APIConst.PRIVATE_CHANNEL, Response.success(message), SimpMessageHeaderAccessorFactory.getMessageHeaders(receiver.getSessionId()));
        } else {
            // Receiver is offline
            message.setReadStatus(MessageConst.UNREAD);
        }

        // Update conversation
        Conversation conversation = conversationService.findPrivateByUserUuids(sender.getUuid(), receiver.getUuid());

        // Create new conversation
        if (conversation == null) {
            conversation = new Conversation();
            conversation.setType(ConversationConst.TYPE_PRIVATE);
            conversation.setUsers(sender.getUuid() + ConversationConst.SPLITTER + receiver.getUuid());
            conversation = conversationService.update(conversation);
        }

        message.setConversationId(conversation.getId());
        message = messageService.update(message);
        conversation.setLastMessage(message);
        conversationService.update(conversation);

        return Response.success(message);
    }

}
