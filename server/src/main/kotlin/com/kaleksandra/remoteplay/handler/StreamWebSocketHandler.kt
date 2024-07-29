package com.kaleksandra.remoteplay.handler

import com.kaleksandra.remoteplay.config.TestConfig
import com.kaleksandra.remoteplay.ext.extractTokenFromSession
import com.kaleksandra.remoteplay.storage.SessionStorage
import org.springframework.stereotype.Component
import org.springframework.web.socket.*

@Component
class StreamWebSocketHandler(
    config: TestConfig
) : WebSocketHandler {

    private val token = config.testToken
    private val sessionStorage: SessionStorage = SessionStorage()

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        if (extractTokenFromSession(session) == token && session.uri?.query?.split("=")?.getOrNull(2) != null) {
            sessionStorage.addSession(session)
        }
        session.binaryMessageSizeLimit = 300000
        println("Client stream connected: " + session.id)
    }

    @Throws(Exception::class)
    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        if (extractTokenFromSession(session) == token) {
            val byteBuffer = (message as BinaryMessage).payload
            sessionStorage.sendMessage(BinaryMessage(byteBuffer))
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