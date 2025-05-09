package com.waseem.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideServer(): Server {
        //TODO: Get server based on product flavor
        return Server.Staging
    }

    @Provides
    fun provideNetworkClient(server: Server): NetworkClient {
        return NetworkClient(server)
    }
}