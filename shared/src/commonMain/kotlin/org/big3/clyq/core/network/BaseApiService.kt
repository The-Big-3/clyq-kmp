package org.big3.clyq.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.util.toMap
import io.ktor.utils.io.errors.IOException

abstract class BaseApiService {


    protected suspend fun <T> makeRequest(
        client: HttpClient,
        call: suspend (HttpClient) -> HttpResponse,
        responseParser: suspend (String) -> T
    ): RemoteResponse<T> {
        return try {
            val response = call(client)
            val responseBody = response.bodyAsText()
            val singleValueHeaders = response.headers.toMap().mapValues { it.value.firstOrNull() ?: "" }

            if (responseBody.isNotEmpty()) {
                val parsedResponse = responseParser(responseBody)
                RemoteResponseWrapper.createSuccess(
                    data = parsedResponse,
                    headers = singleValueHeaders
                )
            } else {
                RemoteResponseWrapper.createError(RemoteErrorWrapper.ParseError)
            }
        } catch (e: Exception) {
            val errorResponse = when (e) {
                is ResponseException -> {
                    RemoteErrorWrapper.ResponseErrorWrapper(
                        e.message ?: "Unknown error",
                        e.response.status.value
                    )
                }

                is IOException -> {
                    RemoteErrorWrapper.NetworkError
                }

                else -> {
                    RemoteErrorWrapper.ParseError
                }
            }
            RemoteResponseWrapper.createError(errorResponse)
        }
    }

}