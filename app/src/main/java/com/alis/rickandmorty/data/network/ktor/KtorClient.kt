package com.alis.rickandmorty.data.network.ktor

import com.alis.rickandmorty.constants.NetworkConstants
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorClient {

    private val client = HttpClient {
        defaultRequest {
            host = NetworkConstants.HOST
            url {
                protocol = URLProtocol.HTTPS
            }
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }

    fun provideClient() = client
}