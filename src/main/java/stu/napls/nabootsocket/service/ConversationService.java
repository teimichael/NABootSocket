package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.Conversation;

import java.util.List;

public interface ConversationService {

    Conversation findById(long id);

    Conversation findByUuid(String uuid);

    List<Conversation> findByUserUuid(String uuid);

    Conversation findPrivateByUserUuids(String uuid0, String uuid1);

    Conversation update(Conversation conversation);
}
