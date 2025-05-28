package com.libroom.auth.data.di

import com.libroom.auth.data.DefaultRepository
import com.libroom.auth.domain.AuthRepository
import com.libroom.auth.domain.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(
        impl: DefaultRepository
    ): AuthRepository

    companion object {

        @Provides
        fun provideLoginUseCase(
            authRepository: AuthRepository
        ) = LoginUseCase(authRepository = authRepository)
    }
}