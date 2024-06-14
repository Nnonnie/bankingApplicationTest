package com.nnech.Ndiulor.presentation.transaction.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebank.databinding.TransactionListItemBinding
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import com.nnech.Ndiulor.presentation.base.Binder
import com.nnech.Ndiulor.presentation.utils.Utils

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    Binder<FinancialTransaction> {

    private val itemBinding = TransactionListItemBinding.bind(itemView)

    override fun bind(data: FinancialTransaction) {
        if (Utils.calcAmount(
                data.amount,
                data.fee
            ).toDouble() < 0
        ) {
            itemBinding.textAmount.setTextColor(Color.RED)
        } else {
            itemBinding.textAmount.setTextColor(Color.GREEN)
        }
        itemBinding.textAmount.text =
            Utils.calcAmount(data.amount, data.fee)
        itemBinding.textDate.text = Utils.convertDate(data.date.toString())
        itemBinding.textDetails.text = data.description.toString()
    }

    fun onTransactionClicked(functionTransaction: () -> Unit) {
        itemBinding.root.setOnClickListener {
            functionTransaction()
        }
    }
}