package com.waseem.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.delete
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.plugins.resources.put
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class NetworkClient(
    server: Server
) {
    val client = HttpClient(Android) {

        install(HttpTimeout) {
            val timeout = 300000L
            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
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
        // TODO: handle errors like 401
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
        return response.body<ResponseType>()
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