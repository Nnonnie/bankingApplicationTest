package com.nnech.Ndiulor.data.repository.remote.dto

import java.util.*

data class FinancialTransactionDTO(
    val id: Int?,
    val date: Date?,
    val amount: Double?,
    val fee: Double?,
    val description: String?
)