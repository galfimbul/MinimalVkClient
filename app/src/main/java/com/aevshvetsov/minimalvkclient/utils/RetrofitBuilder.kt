package com.aevshvetsov.minimalvkclient.utils

import com.aevshvetsov.minimalvkclient.adapters.AttachmentsTypeAdapter
import com.aevshvetsov.minimalvkclient.models.networkmodels.Attachment
import com.aevshvetsov.minimalvkclient.network.FeedApi
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alexander Shvetsov on 24.06.2020
 */
object RetrofitBuilder {
    var token: String = ""
    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .registerTypeAdapter(Attachment::class.java, AttachmentsTypeAdapter())
            .create()
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder().addQueryParameter(Constants.ACCESS_TOKEN_KEY, token).build()
            val request = chain.request().newBuilder().url(url).build()
            return@Interceptor chain.proceed(request)

        }
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val feedApi: FeedApi = getRetrofit().create(FeedApi::class.java)
}