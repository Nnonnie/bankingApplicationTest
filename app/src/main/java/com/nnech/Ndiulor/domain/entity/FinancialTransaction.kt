package com.nnech.Ndiulor.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Transactions")
data class FinancialTransaction(
    @PrimaryKey
    val id: Int?,
    val date: Date?,
    val amount: Double?,
    val fee: Double?,
    val description: String?
)