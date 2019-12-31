package stu.napls.nabootsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.nabootsocket.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

