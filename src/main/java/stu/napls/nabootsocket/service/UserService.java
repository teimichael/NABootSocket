package stu.napls.nabootsocket.service;

import stu.napls.nabootsocket.model.User;

public interface UserService {
    User findUserByUuid(String uuid);

    User findUserBySessionId(String sessionId);

    User update(User user);
}
