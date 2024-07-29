package com.kaleksandra.remoteplay.storage

import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

class SessionStorage {
    private val sessions: MutableMap<String, WebSocketSession> = ConcurrentHashMap()

    fun addSession(session: WebSocketSession) {
        sessions[session.id] = session
    }

    fun removeSession(session: WebSocketSession) {
        sessions.remove(session.id)
    }

    fun getSessionById(sessionId: String): WebSocketSession? {
        return sessions[sessionId]
    }

    fun getFirstValue(): WebSocketSession? {
        return sessions.entries.firstOrNull()?.value
    }

    fun sendMessage(message: String) {
        try {
            val clientSession = getFirstValue()
            clientSession?.let {
                if (clientSession.isOpen) {
                    clientSession.sendMessage(TextMessage(message))
                } else {
                    removeSession(clientSession)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun sendMessage(message: BinaryMessage) {
        try {
            val clientSession = getFirstValue()
            clientSession?.let {
                if (clientSession.isOpen) {
                    clientSession.sendMessage(message)
                } else {
                    removeSession(clientSession)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}