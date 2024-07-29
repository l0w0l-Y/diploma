package com.kaleksandra.coredata.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LobbyDTO(
    val room: String,
)