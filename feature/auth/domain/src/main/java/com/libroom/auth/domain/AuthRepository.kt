package com.libroom.auth.domain

interface AuthRepository {
    suspend fun login(email: String, password: String)
}