package com.waseem.core.network

sealed class Server(
    val host: String,
    val port: Int?
) {
    data object Local : Server(host = "192.168.17.1", port = 8080)
    data object Staging : Server(host = "4811edba-3db2-4968-8fa6-ebb6d7c52e82.mock.pstmn.io", port = null)
    data object Production: Server(host = "api.sample.com", port = null)
}