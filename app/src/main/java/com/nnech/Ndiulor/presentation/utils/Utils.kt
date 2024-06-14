package com.nnech.Ndiulor.presentation.utils

import android.util.Log
import com.nnech.Ndiulor.domain.entity.FinancialTransaction
import java.math.RoundingMode
import java.text.ParseException
import java.text.SimpleDateFormat

class Utils {
    companion object {
        fun convertStringToInt(str: String): Int {
            return Integer.parseInt(str)
        }

        fun convertDate(dateStr: String): String {

            var result = ""
            try {
                val parser = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy")
                val date = parser.parse(dateStr)
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val formattedDate = formatter.format(date)
                result = formattedDate
            } catch (e: ParseException) {
                Log.e("Date Parse Exception", e.toString())
            }
            return result
        }

        fun calcAmount(amount: Double?, fee: Double?): String {
            return if (amount != null && fee != null) {
                (amount - fee).toString()
            } else {
                amount.toString()
            }
        }

        fun calcTotalAmount(items: List<FinancialTransaction>): String {
            var total = 0.0
            var oper = 0.0
            for (item: FinancialTransaction in items.reversed()) {
                if (item.fee != null && item.amount != null) {
                    oper = item.amount - item.fee
                } else if (item.amount != null) {
                    oper = item.amount
                }
                total += oper
            }
            return total.toBigDecimal().setScale(3, RoundingMode.HALF_EVEN).toDouble().toString()
        }
    }
}