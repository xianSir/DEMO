package learn.springWebSocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author xks
 * @date 2019-08-06
 */
public class myWebSocket implements WebSocketHandler {
    /**
     * Return the list of sub-protocols supported by this handler.
     * <p>By default an empty list is returned.
     */
    @Override
    public List<String> getSubProtocols() {
        return null;
    }

    /**
     * Invoked when a new WebSocket connection is established, and allows
     * handling of the session.
     *
     * <p>See the class-level doc and the reference for more details and
     * examples of how to handle the session.
     *
     * @param session the session to handle
     * @return indicates when appilcation handling of the session is complete,
     * which should reflect the completion of the inbound message stream
     * (i.e. connection closing) and possibly the completion of the outbound
     * message stream and the writing of messages.
     */
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return null;
    }
}
