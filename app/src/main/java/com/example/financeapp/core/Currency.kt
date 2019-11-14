package com.example.financeapp.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Currency(val symbol: String = "") : Parcelable

@Parcelize
object USD : Currency("$")

@Parcelize
object EUR : Currency("€")

@Parcelize
object RUB : Currency("\u20BD")
