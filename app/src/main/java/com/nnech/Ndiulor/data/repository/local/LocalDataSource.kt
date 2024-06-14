package com.nnech.Ndiulor.data.repository.local

import com.nnech.Ndiulor.data.repository.local.db.TransactionDao
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val transactionDao: TransactionDao
) {

    fun getAllTransactions() = transactionDao.getAllTransactions()

    fun getTransaction(id: Int) = transactionDao.getTransaction(id)

    suspend fun insertAll(financialTransactions: List<FinancialTransaction>) =
        transactionDao.insertAll(financialTransactions)
}