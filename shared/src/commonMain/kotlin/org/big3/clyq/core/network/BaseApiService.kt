package org.big3.clyq.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException

abstract class BaseApiService {


    protected suspend fun <T> makeRequest(
        call: suspend (HttpClient) -> HttpResponse,
        headers: Map<String, String> = emptyMap(),
        responseParser: suspend (String) -> T
    ): RemoteResponse<T> {
        return try {
            val result = call(createRequestWithHeaders(headers))

            val responseBody = result.bodyAsText()

            if (responseBody.isNotEmpty()) {
                val parsedResponse = responseParser(responseBody)
                RemoteResponseWrapper.createSuccess(parsedResponse)
            } else {
                RemoteResponseWrapper.createError(RemoteErrorWrapper.ParseError)
            }
        }catch (e: Exception) {
            val errorResponse = when (e) {
                is ResponseException -> {
                    RemoteErrorWrapper.ResponseErrorWrapper(e.message ?: "Unknown error", e.response.status.value)
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

    private fun createRequestWithHeaders(headers: Map<String, String>): HttpClient {
        return HttpClient {
            defaultRequest {
                headers.forEach { (key, value) ->
                    header(key, value)
                }
            }
        }
    }
}