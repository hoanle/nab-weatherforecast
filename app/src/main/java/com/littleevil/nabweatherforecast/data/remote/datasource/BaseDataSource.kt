package com.littleevil.nabweatherforecast.data.remote.datasource

import com.littleevil.nabweatherforecast.util.Result
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T, R> getResult(
        call: suspend () -> Response<T>,
        bodyMapping: (T) -> R
    ): Result<R> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(bodyMapping(body))
            }
            return error(response.code(), response.message())
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(code: Int? = null, message: String): Result<T> {
        val mappingMessage = code?.let { if (it == 404) "City not found" else message } ?: message
        return Result.error(code, mappingMessage)
    }

}