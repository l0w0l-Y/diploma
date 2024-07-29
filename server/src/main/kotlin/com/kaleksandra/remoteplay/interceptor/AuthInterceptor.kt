package com.kaleksandra.remoteplay.interceptor

import com.kaleksandra.remoteplay.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class AuthInterceptor(private val tokenRepository: TokenRepository) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            val token = bearerToken.substring(7)
            if (tokenRepository.existsByToken(token)) {
                request.setAttribute(HttpHeaders.AUTHORIZATION, token)
                return true
            }
        }
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.writer.write("Invalid token")
        return false
    }
}
