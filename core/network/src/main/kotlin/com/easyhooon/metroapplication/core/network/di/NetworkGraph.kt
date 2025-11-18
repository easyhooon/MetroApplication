package com.easyhooon.metroapplication.core.network.di

import com.easyhooon.metroapplication.core.di.DataScope
import com.easyhooon.metroapplication.core.network.TokenAuthenticator
import com.easyhooon.metroapplication.core.network.TokenInterceptor
import com.easyhooon.metroapplication.core.network.service.ApiService
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val MaxTimeoutMillis = 15_000L
private const val BASE_URL = "https://api.example.com/"

private val jsonRule = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
}

private val jsonConverterFactory = jsonRule.asConverterFactory("application/json".toMediaType())

@ContributesTo(DataScope::class)
interface NetworkGraph {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .readTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .writeTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .addInterceptor(tokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit,
    ): ApiService {
        return retrofit.create()
    }
}