package com.easyhooon.metroapplication.core.ocr.di

import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private const val MaxTimeoutMillis = 15_000L
private const val CLOUD_VISION_BASE_URL = "https://vision.googleapis.com/"

private val jsonRule = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
}

private val jsonConverterFactory = jsonRule.asConverterFactory("application/json".toMediaType())

@ContributesTo(DataScope::class)
interface OcrGraph {

    @CloudVisionOkHttp
    @Provides
    fun provideCloudVisionOkHttpClient(): OkHttpClient {
        val log = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(log)
            .connectTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .readTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .writeTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
            .build()
    }

    @CloudVisionRetrofit
    @Provides
    fun provideCloudVisionRetrofit(
        @CloudVisionOkHttp okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CLOUD_VISION_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }
}
