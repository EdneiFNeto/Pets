package com.pets.di

import com.pets.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("x-api-key", BuildConfig.API_KEY)
        return chain.proceed(requestBuilder.build())
    }
}