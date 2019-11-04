package com.example.financeapp.account

import android.os.Parcelable
import com.example.financeapp.core.Money
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account (
    val name: String,
    val type: AccountType,
    val amount: Money
) : Parcelable