package com.example.financeapp.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Currency(val symbol: String = "", val code: String = "") : Parcelable

@Parcelize
object USD : Currency("$", "USD")

@Parcelize
object EUR : Currency("€", "EUR")

@Parcelize
object RUB : Currency("\u20BD", "RUB")
