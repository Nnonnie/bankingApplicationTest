package com.nnech.Ndiulor.presentation.transaction.view.activity

import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.samplebank.R
import com.nnech.Ndiulor.core.Resource
import com.bielfernandezb.samplebank.databinding.ActivityMainBinding
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import com.nnech.Ndiulor.presentation.BaseActivity
import com.nnech.Ndiulor.presentation.navigation.Navigator
import com.nnech.Ndiulor.presentation.transaction.adapter.TransactionAdapter
import com.nnech.Ndiulor.presentation.transaction.viewmodel.MainViewModel
import com.nnech.Ndiulor.presentation.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: TransactionAdapter<FinancialTransaction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = createTransactionAdapter(emptyList())
        binding.recyclerTransactions.layoutManager = LinearLayoutManager(this)
        binding.recyclerTransactions.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerTransactions.context,
            (binding.recyclerTransactions.layoutManager as LinearLayoutManager).orientation
        )
        binding.recyclerTransactions.addItemDecoration(dividerItemDecoration)
    }

    private fun createTransactionAdapter(list: List<FinancialTransaction>): TransactionAdapter<FinancialTransaction> {
        return object : TransactionAdapter<FinancialTransaction>(
            list,
            { it.id?.let { it1 -> onClickedFinancialTransaction(it1) } }
        ) {
            override fun getLayoutId(position: Int, obj: FinancialTransaction): Int {
                return R.layout.transaction_list_item
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return HolderFactory.create(view, viewType)
            }
        }
    }

    private fun setupObservers() {
        viewModel.transactions.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        adapter.setItems(ArrayList(it.data))
                        val accountBalance: String =
                            Utils.calcTotalAmount(it.data.toList())
                        binding.textAccountBalance.text =
                            accountBalance
                                .plus("€")
                        if (accountBalance.toDouble() < 0) binding.textAccountBalance.setTextColor(
                            RED
                        ) else
                            binding.textAccountBalance.setTextColor(GREEN)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
        binding.swipe.setOnRefreshListener {
            viewModel.refreshTransactions()
            binding.swipe.isRefreshing = false
        }
    }


    private fun onClickedFinancialTransaction(financialTransactionId: Int) {
        Navigator().navigateToTransactionDetails(this, financialTransactionId)
    }
}