package com.kaleksandra.coredata.network.api

import com.kaleksandra.coredata.network.model.GameDTO
import com.kaleksandra.coredata.network.model.LobbyDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ActionApi {
    @POST("action")
    suspend fun setAction(@Body action: String): Response<Unit>

    @POST("controller/lobby")
    suspend fun createRoom(): Response<LobbyDTO>

    @POST("controller/game")
    suspend fun createRoom(@Body lobbyId: Long): Response<LobbyDTO>

    @GET("controller/games")
    suspend fun getGames(): Response<List<GameDTO>>

    @GET("controller/games")
    suspend fun searchGames(@Query("query") value: String): Response<List<GameDTO>>
}