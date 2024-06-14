package com.nnech.Ndiulor.data.di

import android.content.Context
import com.nnech.Ndiulor.data.repository.TransactionsRepositoryImpl
import com.nnech.Ndiulor.data.repository.local.LocalDataSource
import com.nnech.Ndiulor.data.repository.local.db.AppDatabase
import com.nnech.Ndiulor.data.repository.remote.RemoteDataSource
import com.nnech.Ndiulor.data.repository.remote.api.APIClient
import com.nnech.Ndiulor.data.repository.remote.api.APIClient.okHttpClient
import com.nnech.Ndiulor.data.repository.remote.api.TransactionsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(APIClient.BASE_URL)
        .addConverterFactory(APIClient.gSONConverter)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideTransactionsService(retrofit: Retrofit): TransactionsService =
        retrofit.create(TransactionsService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTransactionDao(db: AppDatabase) = db.transactionDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) = TransactionsRepositoryImpl(remoteDataSource, localDataSource)
}