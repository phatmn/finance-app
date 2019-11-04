package com.example.financeapp.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Currency : Parcelable

@Parcelize
data class USD(val symbol: String = "$") : Currency()

@Parcelize
data class EUR(val symbol: String = "€") : Currency()

@Parcelize
data class RUB(val symbol: String = "\u20BD") : Currency()
