package stu.napls.nabootsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.nabootsocket.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationUuid(String conversationId);

}

