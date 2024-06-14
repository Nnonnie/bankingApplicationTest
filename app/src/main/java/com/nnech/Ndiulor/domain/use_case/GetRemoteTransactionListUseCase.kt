package com.nnech.Ndiulor.domain.use_case

import androidx.lifecycle.LiveData
import com.nnech.Ndiulor.core.Resource
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import com.nnech.Ndiulor.domain.repository.TransactionsRepository
import com.nnech.Ndiulor.domain.use_case.BaseUseCase
import javax.inject.Inject

class GetRemoteTransactionListUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : BaseUseCase<Unit, LiveData<Resource<List<FinancialTransaction>>>> {

    override fun invoke(params: Unit): LiveData<Resource<List<FinancialTransaction>>> =
        transactionsRepository.getTransactions()

}