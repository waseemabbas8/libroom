package com.libroom.auth.domain

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> = runCatching {
        //TODO: validate email here
        if(email.isBlank()) {
            throw Exception("Invalid email")
        }
        authRepository.login(email, password)
    }
}