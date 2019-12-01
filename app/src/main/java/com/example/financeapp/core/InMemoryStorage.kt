package com.example.financeapp.core

import com.example.financeapp.operation.Operation
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

object InMemoryStorage {
    private var ops : MutableList<Operation> = arrayListOf()

    fun createObservable() : Observable<Operation> {
        return ops.toObservable()
    }

    fun fromCollection(ops : MutableCollection<Operation>) {
        this.ops = ops as MutableList<Operation>
    }

    fun addOperation(op : Operation) {
        ops.add(op)
    }
}