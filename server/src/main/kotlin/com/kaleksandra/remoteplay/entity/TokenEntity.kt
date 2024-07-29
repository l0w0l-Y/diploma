package com.kaleksandra.remoteplay.entity

import jakarta.persistence.*

@Entity(name = "tokens")
class TokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0L,
    val token: String = "",
)