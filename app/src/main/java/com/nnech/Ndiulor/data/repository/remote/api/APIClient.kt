package com.nnech.Ndiulor.data.repository.remote.api

import com.nnech.Ndiulor.data.repository.remote.serializer.DateJSONParser
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InterruptedIOException
import java.util.*
import java.util.concurrent.TimeUnit

object APIClient {

    const val BASE_URL = "https://api.npoint.io/"

    val okHttpClient: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val interceptorConnection = Interceptor { chain -> onOnIntercept(chain) }
            return OkHttpClient.Builder()
                .connectTimeout(70, TimeUnit.SECONDS)
                .writeTimeout(70, TimeUnit.SECONDS)
                .readTimeout(70, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .addInterceptor(interceptorConnection)
                .build()
        }

    val gSONConverter: GsonConverterFactory
        get() {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(Date::class.java, DateJSONParser())
            val gson = gsonBuilder.create()
            return GsonConverterFactory.create(gson)
        }

    @Throws(IOException::class)
    private fun onOnIntercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        try {
            response = chain.proceed(chain.request())
        } catch (e: IOException) {
            if (e is InterruptedIOException) {
                throw InterruptedIOException()
            }
        }
        if (response == null) throw IOException()
        return response
    }
}
