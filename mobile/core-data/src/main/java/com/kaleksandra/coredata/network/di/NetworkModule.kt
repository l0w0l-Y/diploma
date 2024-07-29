package com.kaleksandra.coredata.network.di

import com.kaleksandra.coredata.network.api.ActionApi
import com.kaleksandra.coredata.network.api.AuthApi
import com.kaleksandra.coredata.network.builder
import com.kaleksandra.coredata.network.client
import com.kaleksandra.coredata.network.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideActionApi(
        authInterceptor: AuthInterceptor
    ): ActionApi =
        builder().client(listOf(authInterceptor)).build().create(ActionApi::class.java)

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi =
        builder().client().build().create(AuthApi::class.java)
}