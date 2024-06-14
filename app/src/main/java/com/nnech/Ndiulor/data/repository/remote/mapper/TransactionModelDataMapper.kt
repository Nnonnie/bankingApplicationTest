package com.nnech.Ndiulor.data.repository.remote.mapper

import com.nnech.Ndiulor.core.Resource
import com.nnech.Ndiulor.data.repository.remote.dto.FinancialTransactionDTO
import com.nnech.Ndiulor.domain.entity.FinancialTransaction

class TransactionModelDataMapper {

    fun transform(transactionResource: Resource<List<FinancialTransactionDTO>>?): Resource<List<FinancialTransaction>> {
        val transactions = mutableListOf<FinancialTransaction>()
        transactionResource?.data?.forEach { it ->
            val transaction = FinancialTransaction(
                it.id,
                it.date,
                it.amount,
                it.fee,
                it.description
            )
            transactions.add(transaction)
        }

        return Resource(transactionResource!!.status, transactions, transactionResource.message)
    }
}