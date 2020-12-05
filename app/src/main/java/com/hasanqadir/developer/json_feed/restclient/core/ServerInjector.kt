package com.hasanqadir.developer.json_feed.restclient.core

import com.google.gson.*
import com.hasanqadir.developer.json_feed.BuildConfig
import com.hasanqadir.developer.json_feed.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Singleton class for network requests
class ServerInjector private constructor() {

    companion object {
        @Volatile
        private var INSTANCE: ServerInjector? = null

        fun getInstance(): ServerInjector {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = ServerInjector()
                }
            }
            return INSTANCE!!
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
        return builder.build()
    }

    private fun provideRetrofit(): Retrofit {
        val httpClient = getOkHttpClient()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    fun provideApiService(): APIService {
        return provideRetrofit()
            .create(APIService::class.java)
    }
}