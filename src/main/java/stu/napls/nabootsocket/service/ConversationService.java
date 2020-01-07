package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.Conversation;
import stu.napls.nabootsocket.model.User;

import java.util.List;
import java.util.Set;

public interface ConversationService {

    List<Conversation> findByUsersUuid(String uuid);

    Conversation findPrivateByUsers(String uuid0, String uuid1);

    Conversation update(Conversation conversation);
}
