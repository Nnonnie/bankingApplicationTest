package com.nnech.Ndiulor.presentation.transaction.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebank.R
import com.bielfernandezb.samplebank.databinding.TransactionListItemBinding
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import com.nnech.Ndiulor.presentation.base.BaseAdapter

abstract class TransactionAdapter<T>(
    list: List<T>,
    private val onTransactionClicked: ((data: FinancialTransaction) -> Unit),
) : BaseAdapter<T>(list.toMutableList()) {

    private lateinit var binding: TransactionListItemBinding

    fun setItems(items: List<T>) {
        list = items.filter { (it as FinancialTransaction).date != null }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_list_item, parent, false)
        return TransactionViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as TransactionViewHolder).onTransactionClicked { onTransactionClicked((list[position] as FinancialTransaction)) }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    object HolderFactory {
        fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
            return TransactionViewHolder(view)
        }
    }
}