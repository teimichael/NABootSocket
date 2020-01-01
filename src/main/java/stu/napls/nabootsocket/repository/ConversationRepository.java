package stu.napls.nabootsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.nabootsocket.model.Conversation;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findByUsersUuid(String uuid);

}

