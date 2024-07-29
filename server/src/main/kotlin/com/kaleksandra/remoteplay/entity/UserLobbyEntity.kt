package com.kaleksandra.remoteplay.entity

import jakarta.persistence.*

@Entity(name = "users_lobby")
data class UserLobbyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "lobby_id")
    val lobbyId: Long = 0L,
    @Column(name = "user_id")
    val userId: Long = 0L,
)