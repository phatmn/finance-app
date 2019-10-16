package com.example.financeapp.operation

import com.example.financeapp.category.Category

data class Operation(val amount: Double) {
    val category : Category = Category("")
    val comment : String = ""
}
