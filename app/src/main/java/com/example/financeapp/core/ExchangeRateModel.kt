package com.example.financeapp.core

import java.math.BigDecimal
import java.util.*

object ExchangeRateModel {
    data class Result(
        val rates: Map<String, BigDecimal>,
        val base: Currency,
        val date: Date
    )
//    data class Rate(val currency: Currency, val rate: BigDecimal)
}