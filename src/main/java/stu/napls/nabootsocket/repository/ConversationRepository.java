package stu.napls.nabootsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.nabootsocket.model.Conversation;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Conversation findByUuid(String uuid);

    List<Conversation> findByUsersContaining(String uuid);

    List<Conversation> findByTypeAndUsersContaining(int type, String uuid);

}

