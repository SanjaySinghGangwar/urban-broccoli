package dev.sanjaygangwar.tempproject.di


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.sanjaygangwar.tempproject.repository.offline.AppDatabase
import dev.sanjaygangwar.tempproject.repository.offline.dao.mainDao
import dev.sanjaygangwar.tempproject.repository.online.APIService
import dev.sanjaygangwar.tempproject.repository.online.RemoteDataSource
import dev.sanjaygangwar.tempproject.repository.online.Repository
import dev.sanjaygangwar.tempproject.repository.sharedpreferences.AppSharePreference
import dev.sanjaygangwar.tempproject.utils.network.Const.BaseUrl
import dev.sanjaygangwar.tempproject.utils.network.Const.timeOutTime
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(timeOutTime, TimeUnit.SECONDS)
                    .writeTimeout(timeOutTime, TimeUnit.SECONDS)
                    .readTimeout(timeOutTime, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideProductsRemoteDataSource(
        apiService: APIService,
        sharedPreferences: AppSharePreference
    ) =
        RemoteDataSource(apiService, sharedPreferences)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)


    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext appContext: Context) =
        AppSharePreference(appContext)

    @Singleton
    @Provides
    fun provideMainDao(db: AppDatabase) = db.mainDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: mainDao
    ) =
        Repository(remoteDataSource, localDataSource)
}