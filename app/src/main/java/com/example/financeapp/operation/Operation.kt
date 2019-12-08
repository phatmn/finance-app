package com.example.financeapp.operation

import android.os.Parcelable
import com.example.financeapp.category.Category
import com.example.financeapp.core.Money
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Operation(
    val amount: Money = Money(),
    val category : Category = Category(),
    val comment : String = ""
) : Parcelable