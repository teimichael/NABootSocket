package stu.napls.nabootsocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import stu.napls.nabootsocket.core.exception.Assert;
import stu.napls.nabootsocket.model.User;
import stu.napls.nabootsocket.service.UserService;

import javax.annotation.Resource;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Resource
    private UserService userService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent sessionConnectedEvent) {

//        System.out.println(sessionConnectedEvent.getMessage().getHeaders());

    }

    @EventListener(SessionDisconnectEvent.class)
    public void handleWebsocketDisconnectListener(SessionDisconnectEvent event) {
        User user = userService.findUserBySessionId(event.getSessionId());
        if (user != null) {
            user.setSessionId(null);
            userService.update(user);
        }
    }

}
