package com.waseem.core.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.delete
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.plugins.resources.put
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class NetworkClient(
    server: Server
) {
    val client = HttpClient(Android) {
        expectSuccess = true
        HttpResponseValidator {
            handleResponseException { exception, _ ->
                Log.e("NetworkClient", "Exception: $exception")
                throw when(exception) {
                    is ClientRequestException, is ServerResponseException -> {
                        val errorBody = try {
                            exception.response.body<RequestErrorBody>()
                        } catch (e: Exception) {
                            Log.e("NetworkClient", "Failed to parse error body $e")
                            null
                        }
                        val errorMessage = errorBody?.message ?: ""
                        Exception(errorMessage, exception)
                    }
                    is HttpRequestTimeoutException -> {
                        Exception("Request timed out. Please check your internet connection and try again.", exception)
                    }
                    else -> exception
                }
            }
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            val timeout = 30000L
            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    //ignoreUnknownKeys = true
                }
            )
        }
        install(Resources)
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = server.host
                port = server.port ?: DEFAULT_PORT
            }
        }
    }

    operator fun invoke(): HttpClient = client

    suspend inline fun <reified Resource: Any, reified ResponseType: Any> get(
        resource: Resource,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): ResponseType {
        val response = client.get(resource = resource, builder = builder)
        return response.body<ResponseType>()
    }

    suspend inline fun <reified Resource: Any, reified ResponseType: Any> post(
        resource: Resource,
        requestBody: Any? = null,
        contentType: ContentType = ContentType.Application.Json,
        builder: HttpRequestBuilder.() -> Unit = {
            contentType(contentType)
            requestBody?.let { setBody(it) }
        }
    ): ResponseType {
        val response = client.post(resource = resource, builder = builder)
        if (response.status.isSuccess()) {
            return response.body<ResponseType>()
        }
        throw ResponseException(response, response.bodyAsText())
    }

    suspend inline fun <reified Resource: Any, reified ResponseType: Any> put(
        resource: Resource,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): ResponseType {
        val response = client.put(resource = resource, builder = builder)
        return response.body<ResponseType>()
    }

    suspend inline fun <reified Resource: Any, reified ResponseType: Any> delete(
        resource: Resource,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): ResponseType {
        val response = client.delete(resource = resource, builder = builder)
        return response.body<ResponseType>()
    }
}