package com.example.financeapp.operation

import android.os.Parcelable
import com.example.financeapp.category.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Operation(
    val amount: Double,
    val category : Category,
    val comment : String
) : Parcelable