package com.example.huma.data

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if(!response.isSuccessful) {
            throw Exception("Error: ${response.code}, ${response.message}")
        }
        return response
    }
}