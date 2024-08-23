package com.example.qrcode.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideQRCodeGenerator(): QRCodeGenerator = QRCodeGenerator()


    @Singleton
    @Provides
    fun provideApi(): Api {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client)
            .baseUrl("https://www.tronalddump.io").build().create(Api::class.java)
    }

}