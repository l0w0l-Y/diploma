package com.kaleksandra.remoteplay.handler

import com.kaleksandra.remoteplay.config.TestConfig
import com.kaleksandra.remoteplay.ext.extractTokenFromSession
import com.kaleksandra.remoteplay.storage.SessionStorage
import org.springframework.stereotype.Component
import org.springframework.web.socket.*

@Component
class MovementWebSocketHandler(
    config: TestConfig
) : WebSocketHandler {
    private val token = config.testToken
    private val sessionStorage: SessionStorage = SessionStorage()
    override fun afterConnectionEstablished(session: WebSocketSession) {
        if (extractTokenFromSession(session) == token) {
            if (session.uri?.query?.split("=")?.getOrNull(2) == null) {
                sessionStorage.addSession(session)
            }
            println("Client movement connected: " + session.id)
        }
        session.binaryMessageSizeLimit = 300000
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        if (extractTokenFromSession(session) == token) {
            if (message.payloadLength != 0) {
                val byteBuffer = (message as TextMessage).payload
                sessionStorage.sendMessage(byteBuffer)
            }
        }
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        println("Transport error: " + session.id)
        exception.printStackTrace()
    }

    override fun afterConnectionClosed(
        session: WebSocketSession,
        closeStatus: CloseStatus
    ) {
        println("Connection closed: " + session.id)
        if (session.uri?.query?.split("=")?.getOrNull(2) != null) {
            sessionStorage.removeSession(session)
        }
    }

    override fun supportsPartialMessages(): Boolean {
        return false
    }
}