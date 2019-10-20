package com.example.financeapp.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category (
    val name: String = "Category"
) : Parcelable