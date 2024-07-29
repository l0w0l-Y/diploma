package com.kaleksandra.remoteplay.config

import com.kaleksandra.remoteplay.handler.MovementWebSocketHandler
import com.kaleksandra.remoteplay.handler.StreamWebSocketHandler
import com.kaleksandra.remoteplay.handler.UserWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val movementWebSocketHandler: MovementWebSocketHandler,
    private val userWebSocketHandler: UserWebSocketHandler,
    private val streamWebSocketHandler: StreamWebSocketHandler
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
            .addHandler(streamWebSocketHandler, "/imageSocket")
            .addHandler(movementWebSocketHandler, "/movement")
            .addHandler(userWebSocketHandler, "/users")
    }
}