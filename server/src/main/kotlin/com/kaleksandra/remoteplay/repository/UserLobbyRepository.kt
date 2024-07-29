package com.kaleksandra.remoteplay.repository

import com.kaleksandra.remoteplay.entity.UserLobbyEntity
import org.springframework.data.repository.CrudRepository

interface UserLobbyRepository : CrudRepository<UserLobbyEntity, Long> {
    fun findByLobbyId(id: Long) : List<UserLobbyEntity>

    fun findByUserId(id:Long) : UserLobbyEntity?
}