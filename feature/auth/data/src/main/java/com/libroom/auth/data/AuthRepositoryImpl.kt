package com.libroom.auth.data

import com.libroom.auth.domain.AuthRepository
import com.libroom.core.data.api.UserDto
import com.waseem.core.network.NetworkClient

class AuthRepositoryImpl(
    private val networkClient: NetworkClient
) : AuthRepository {
    override suspend fun login(email: String, password: String) {
        networkClient.post<AuthResource.Login, UserDto>(
            resource = AuthResource.Login(),
            requestBody = mapOf("email" to email, "password" to password)
        )
        //TODO: save the user info in preferences
    }
}