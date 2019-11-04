package com.example.financeapp.account

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class AccountType : Parcelable

@Parcelize
object Cash : AccountType()

@Parcelize
object Card : AccountType()
