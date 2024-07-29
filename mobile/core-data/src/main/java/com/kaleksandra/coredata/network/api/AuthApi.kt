package com.kaleksandra.coredata.network.api

import com.kaleksandra.coredata.network.model.AuthRequest
import com.kaleksandra.coredata.network.model.TokenDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("controller/auth")
    suspend fun auth(@Body body: AuthRequest) : Response<TokenDTO>
}