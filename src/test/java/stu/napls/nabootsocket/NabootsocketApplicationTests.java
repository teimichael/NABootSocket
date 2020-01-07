package stu.napls.nabootsocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import stu.napls.nabootsocket.service.ConversationService;
import stu.napls.nabootsocket.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
class NabootsocketApplicationTests {

    @Resource
    private ConversationService conversationService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {

    }

}
