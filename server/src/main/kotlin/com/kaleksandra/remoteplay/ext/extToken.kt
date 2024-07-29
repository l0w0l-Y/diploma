package com.kaleksandra.remoteplay.ext

import org.springframework.web.socket.WebSocketSession

fun getToken(bearerToken: String): String? {
    return if (bearerToken.startsWith("Bearer ")) {
        bearerToken.substring(7)
    } else null
}

fun extractTokenFromSession(session: WebSocketSession): String? {
    return session.uri?.query?.split("=")?.get(1)
}