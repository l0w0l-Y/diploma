package com.kaleksandra.remoteplay.controller

import com.kaleksandra.remoteplay.dto.TokenDTO
import com.kaleksandra.remoteplay.entity.TokenEntity
import com.kaleksandra.remoteplay.repository.TokenRepository
import com.kaleksandra.remoteplay.repository.UserRepository
import com.kaleksandra.remoteplay.request.AuthRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/controller/auth")
class AuthController @Autowired constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
) {
    @PostMapping("")
    fun auth(@RequestBody body: AuthRequest): ResponseEntity<TokenDTO> {
        val user = userRepository.findByEmail(body.email)
        if (user?.password == body.password) {
            val now = Date()
            val token = UUID.randomUUID().toString() + user.id + '-' + now.time
            tokenRepository.save(TokenEntity(user.id, token))
            return ResponseEntity.ok(TokenDTO(token))
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }
}