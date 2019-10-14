package com.example.financeapp.category

class Category {
    var name = "Category"

    fun setName(name: String): Category {
        this.name = name
        return this
    }

    companion object {
        fun create(): Category {
            return Category()
        }
    }
}