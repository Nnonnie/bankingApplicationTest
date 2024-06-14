package com.nnech.Ndiulor.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bielfernandezb.samplebank.R
import com.nnech.Ndiulor.presentation.transaction_details.view.fragment.TransactionDetailsFragment

class Navigator {

    companion object {
        const val TRANSACTION_ID = "transactionId"
    }

    fun navigateToTransactionDetails(activity: AppCompatActivity, transactionId: Int) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val transactionDetailsFragment = TransactionDetailsFragment()
        val bundle = Bundle()
        bundle.putInt(TRANSACTION_ID, transactionId)
        transactionDetailsFragment.arguments = bundle
        fragmentTransaction.add(R.id.mainContent, transactionDetailsFragment)
            .addToBackStack(activity::class.java.toString()).commit()
    }

}