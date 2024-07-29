package com.kaleksandra.remoteplay.entity

import jakarta.persistence.*

@Entity(name = "user_info")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val nickname: String = "",
    val email: String = "",
    val password: String = "",
)