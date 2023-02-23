package com.example.hiltdemo.di

import android.app.Application
import com.example.hiltdemo.BuildConfig
import com.example.hiltdemo.api.MovieApi
import com.example.hiltdemo.dao.MovieDao
import com.example.hiltdemo.db.Database
import com.example.hiltdemo.utils.AppConstant
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.attribute.AclEntry.Builder
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = AppConstant.BASE_URL

    @Provides
    fun createOkHttpClient() : OkHttpClient{
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor()) }
            return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(base_url : String,httpClient: OkHttpClient) : MovieApi =
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(MovieApi::class.java)
    @Provides
    @Singleton
    fun getDatabase(application: Application) : Database{
        return Database.getDB(application)
    }

    @Provides
    @Singleton
    fun getDao(database: Database) : MovieDao{
        return database.getDao()
    }


}