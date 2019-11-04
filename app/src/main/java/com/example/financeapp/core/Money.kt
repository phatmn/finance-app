package com.example.financeapp.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Money (
    val amount: Double = 0.0,
    val currency: Currency
) : Parcelable {
    override fun toString(): String = when (currency) {
            is USD -> currency.symbol + amount
            is EUR -> currency.symbol + amount
            is RUB -> amount.toString() + " " + currency.symbol
        }
    }