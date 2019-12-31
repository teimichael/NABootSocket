package stu.napls.nabootsocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.napls.nabootsocket.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);

    User findBySessionId(String sessionId);
}

