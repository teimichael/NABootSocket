package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findByConversationUuid(String conversationUuid);

    Message update(Message message);
}
