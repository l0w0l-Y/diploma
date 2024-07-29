package com.kaleksandra.remoteplay.repository

import com.kaleksandra.remoteplay.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}