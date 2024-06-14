package com.nnech.Ndiulor.data.repository

import com.nnech.Ndiulor.data.repository.local.LocalDataSource
import com.nnech.Ndiulor.data.repository.remote.RemoteDataSource
import com.nnech.Ndiulor.data.repository.utils.performGetOperation
import com.nnech.Ndiulor.data.repository.utils.performSingleDatabaseGetOperation
import com.nnech.Ndiulor.domain.repository.TransactionsRepository
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : TransactionsRepository {

    override fun getTransactions() = performGetOperation(
        databaseQuery = { localDataSource.getAllTransactions() },
        networkCall = { remoteDataSource.getTransactions() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    override fun getTransaction(id: Int) = performSingleDatabaseGetOperation(
        databaseQuery = { localDataSource.getTransaction(id) }
    )

}