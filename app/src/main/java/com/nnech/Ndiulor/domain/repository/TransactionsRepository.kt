package com.nnech.Ndiulor.domain.repository

import androidx.lifecycle.LiveData
import com.nnech.Ndiulor.core.Resource
import com.nnech.Ndiulor.domain.entity.FinancialTransaction

interface TransactionsRepository {
    fun getTransactions(): LiveData<Resource<List<FinancialTransaction>>>

    fun getTransaction(id: Int): LiveData<Resource<FinancialTransaction>>
}