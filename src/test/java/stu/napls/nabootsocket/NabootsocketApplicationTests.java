package stu.napls.nabootsocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import stu.napls.nabootsocket.core.dictionary.AppCode;
import stu.napls.nabootsocket.model.Conversation;
import stu.napls.nabootsocket.model.User;
import stu.napls.nabootsocket.service.ConversationService;
import stu.napls.nabootsocket.service.UserService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class NabootsocketApplicationTests {

    @Resource
    private ConversationService conversationService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        Conversation conversation = new Conversation();
        Set<User> users = new HashSet<>();
        users.add(userService.findUserByUuid("u0"));
        users.add(userService.findUserByUuid("u1"));
        conversation.setType(AppCode.Conversation.GROUP.getValue());
        conversation.setUsers(users);
        conversationService.update(conversation);
    }

}
