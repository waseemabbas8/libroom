package com.libroom.auth.data.di

import com.libroom.auth.data.AuthRepositoryImpl
import com.libroom.auth.domain.AuthRepository
import com.waseem.core.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object AuthModule {

    @Provides
    fun provideAuthRepository(
        networkClient: NetworkClient
    ): AuthRepository = AuthRepositoryImpl(networkClient = networkClient)
}