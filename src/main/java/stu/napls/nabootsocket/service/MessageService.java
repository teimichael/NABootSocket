package stu.napls.nabootsocket.service;

import org.springframework.data.domain.Pageable;
import stu.napls.nabootsocket.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findByConversationUuid(String conversationUuid, Pageable pageable);

    Message update(Message message);
}
