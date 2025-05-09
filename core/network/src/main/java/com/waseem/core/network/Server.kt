package com.waseem.core.network

sealed class Server(
    val host: String,
    val port: Int?
) {
    data object Local : Server(host = "192.168.17.1", port = 8080)
    data object Staging : Server(host = "run.mocky.io", port = null)
    data object Production: Server(host = "api.sample.com", port = null)
}