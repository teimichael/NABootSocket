package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.Conversation;

import java.util.List;

public interface ConversationService {

    List<Conversation> findByUuid(String uuid);

    Conversation findPrivateByUuids(String uuid0, String uuid1);

    Conversation update(Conversation conversation);
}
