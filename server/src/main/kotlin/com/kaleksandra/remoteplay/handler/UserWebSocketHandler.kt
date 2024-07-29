package com.kaleksandra.remoteplay.handler

import com.kaleksandra.remoteplay.config.TestConfig
import com.kaleksandra.remoteplay.ext.extractTokenFromSession
import com.kaleksandra.remoteplay.repository.UserLobbyRepository
import com.kaleksandra.remoteplay.repository.UserRepository
import com.kaleksandra.remoteplay.storage.SessionStorage
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession

@Component
class UserWebSocketHandler(
    private val userLobbyRepository: UserLobbyRepository,
    private val userRepository: UserRepository,
    config: TestConfig
) : WebSocketHandler {
    private val token = config.testToken
    private val sessionStorage: SessionStorage = SessionStorage()
    override fun afterConnectionEstablished(session: WebSocketSession) {
        if (extractTokenFromSession(session) == token && session.uri?.query?.split("=")?.getOrNull(2) != null) {
            sessionStorage.addSession(session)
        }
        session.binaryMessageSizeLimit = 300000
        println("Client user connected: " + session.id)
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        //TODO("Not yet implemented")
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        println("Transport error: " + session.id)
        exception.printStackTrace()
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        println("Connection closed: " + session.id)
        if (session.uri?.query?.split("=")?.getOrNull(2) != null) {
            sessionStorage.removeSession(session)
        }
    }

    override fun supportsPartialMessages(): Boolean {
        return false
    }
}