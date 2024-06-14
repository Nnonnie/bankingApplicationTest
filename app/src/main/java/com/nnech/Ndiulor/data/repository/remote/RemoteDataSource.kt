package com.nnech.Ndiulor.data.repository.remote

import com.nnech.Ndiulor.data.repository.remote.BaseDataSource
import com.nnech.Ndiulor.data.repository.remote.api.TransactionsService
import com.nnech.Ndiulor.data.repository.remote.mapper.TransactionModelDataMapper
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val transactionsService: TransactionsService
) : BaseDataSource() {

    suspend fun getTransactions() =
        TransactionModelDataMapper().transform(getResult { transactionsService.getAllTransactions() })
}