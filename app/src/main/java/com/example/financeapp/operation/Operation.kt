package com.example.financeapp.operation

class Operation {
    var amount : Double = 0.0
    var category : String = "Category"
    var comment : String = "Comment"

    fun setAmount(amount: Double): Operation {
        this.amount = amount
        return this
    }

    companion object {
        fun create(): Operation {
            return Operation()
        }

    }
}
