package com.example.tapadooapp.core.utils

import java.text.NumberFormat
import java.util.Currency


fun Int.currency(currencyCode: String) : String{
    val currencyFormatter = NumberFormat.getCurrencyInstance()
    currencyFormatter.currency = Currency.getInstance(currencyCode)
    return currencyFormatter.format(this /100.0)
}