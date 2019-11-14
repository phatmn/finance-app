package com.example.financeapp.core

import java.math.BigDecimal
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Money (
    val amount: BigDecimal = BigDecimal.ZERO,
    val currency: Currency
) : Parcelable {
    override fun toString(): String = when (currency) {
            is USD -> currency.symbol + amount
            is EUR -> currency.symbol + amount
            is RUB -> amount.toString() + " " + currency.symbol
        }
    }