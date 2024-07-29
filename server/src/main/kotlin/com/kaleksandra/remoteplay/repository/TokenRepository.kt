package com.kaleksandra.remoteplay.repository

import com.kaleksandra.remoteplay.entity.TokenEntity
import org.springframework.data.repository.CrudRepository

interface TokenRepository : CrudRepository<TokenEntity, Long> {
    fun existsByToken(token: String): Boolean
    fun findByToken(token: String): TokenEntity?
}