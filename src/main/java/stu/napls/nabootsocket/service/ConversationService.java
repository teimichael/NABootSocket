package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.Conversation;

import java.util.List;

public interface ConversationService {

    List<Conversation> findByUsersUuid(String uuid);

    Conversation update(Conversation conversation);
}
