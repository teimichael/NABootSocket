package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findByConversationId(Long conversationId);

    Message update(Message message);
}
