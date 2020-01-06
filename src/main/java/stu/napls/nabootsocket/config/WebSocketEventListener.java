package stu.napls.nabootsocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import stu.napls.nabootsocket.core.exception.Assert;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private static int sessionCounter = 0;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent sessionConnectedEvent) {
        logger.info(String.valueOf(++sessionCounter));
        GenericMessage<?> genericMessage = (GenericMessage<?>) sessionConnectedEvent.getMessage().getHeaders().get("simpConnectMessage");
        Assert.notNull(genericMessage, "Connection failed.");
        Object nativeHeaders = genericMessage.getHeaders().get("nativeHeaders");
        Assert.notNull(nativeHeaders, "Connection failed.");
//        System.out.println(nativeHeaders.toString());

    }

    @EventListener(SessionDisconnectEvent.class)
    public void handleWebsocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info(String.valueOf(--sessionCounter));
    }

}
