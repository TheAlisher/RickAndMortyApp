package com.alis.rickandmorty.data.network.ktor

import com.alis.rickandmorty.constants.Constants
import com.alis.rickandmorty.data.network.okhttp.interceptors.LoggingInterceptor
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class KtorClient {

    private val client = HttpClient(OkHttp) {
        defaultRequest {
            host = Constants.HOST
            url {
                protocol = URLProtocol.HTTPS
            }
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        engine {
            addInterceptor(LoggingInterceptor().provideLoggingInterceptor())

            config {
                connectTimeout(30, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
            }
        }
    }

    fun provideClient() = client
}