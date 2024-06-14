package com.nnech.Ndiulor.presentation.transaction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nnech.Ndiulor.core.Resource
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import com.nnech.Ndiulor.domain.use_case.GetRemoteTransactionListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRemoteTransactionListUseCase: GetRemoteTransactionListUseCase
) : ViewModel() {

    private val reloadTrigger = MutableLiveData<Boolean>()

    val transactions: LiveData<Resource<List<FinancialTransaction>>> =
        Transformations.switchMap(reloadTrigger) {
            getRemoteTransactionListUseCase.invoke(Unit)
        }

    init {
        refreshTransactions()
    }

    fun refreshTransactions() {
        reloadTrigger.value = true
    }

}
