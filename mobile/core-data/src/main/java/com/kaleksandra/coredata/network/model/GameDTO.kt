package com.kaleksandra.coredata.network.model

import kotlinx.serialization.Serializable

@Serializable
data class GameDTO(
    val id: Long = 0L,
    val name: String = "",
    val photo: String = "",
)