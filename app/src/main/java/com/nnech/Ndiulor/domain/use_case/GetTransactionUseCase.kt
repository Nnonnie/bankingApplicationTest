package com.nnech.Ndiulor.domain.use_case

import androidx.lifecycle.LiveData
import com.nnech.Ndiulor.core.Resource
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import com.nnech.Ndiulor.domain.repository.TransactionsRepository
import com.nnech.Ndiulor.domain.use_case.BaseUseCase
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : BaseUseCase<Int, LiveData<Resource<FinancialTransaction>>> {

    override fun invoke(params: Int): LiveData<Resource<FinancialTransaction>> =
        transactionsRepository.getTransaction(params)

}