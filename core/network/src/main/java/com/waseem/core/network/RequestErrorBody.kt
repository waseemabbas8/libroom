package com.waseem.core.network

import kotlinx.serialization.Serializable

@Serializable
data class RequestErrorBody(
    val message: String,
    val status: Int
)