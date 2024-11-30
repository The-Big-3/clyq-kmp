package org.big3.clyq.core.network


sealed class RemoteResponseWrapper<T, E> {
    data class Success<T, E>(val body: T, val headers:Map<String,String>?= null) : RemoteResponseWrapper<T, E>()
    data class Error<T, E>(val error: E,val headers:Map<String,String>?= null) : RemoteResponseWrapper<T, E>()
    companion object {
        fun <T, E> createError(error: E,headers: Map<String, String>? = null): RemoteResponseWrapper<T, E> {
            return Error(error,headers)
        }

        fun <T, E> createSuccess(data: T,headers: Map<String, String>? = null): RemoteResponseWrapper<T, E> {
            return Success(data,headers)
        }
    }
}


sealed class RemoteErrorWrapper: Exception() {
    data class ResponseErrorWrapper(override val message: String, val code: Int) : RemoteErrorWrapper()
    data class ServerError(val error: String?, val description: String?) : RemoteErrorWrapper()
    companion object {
        fun failed(message: String, code: Int): RemoteErrorWrapper {
            return ResponseErrorWrapper(message, code)
        }
    }
}


typealias RemoteResponse<T> = RemoteResponseWrapper<T, RemoteErrorWrapper>



//More simple
//sealed class RemoteResponseWrapper<T> {
//    data class Success<T>(val body: T, val headers: Map<String, String>? = null) : RemoteResponseWrapper<T>()
//    data class Error<T>(val error: RemoteErrorWrapper, val headers: Map<String, String>? = null) : RemoteResponseWrapper<T>()
//    companion object {
//        fun <T> createError(error: RemoteErrorWrapper, headers: Map<String, String>? = null): RemoteResponseWrapper<T> {
//            return Error(error, headers)
//        }
//
//        fun <T> createSuccess(data: T, headers: Map<String, String>? = null): RemoteResponseWrapper<T> {
//            return Success(data, headers)
//        }
//    }
//}
//
//typealias RemoteResponse<T> = RemoteResponseWrapper<T>
