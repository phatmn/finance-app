package com.example.financeapp.core

import com.example.financeapp.operation.Operation
import java.math.BigDecimal

object InMemoryStorage{
    private var callbacks: MutableList<(action: Action, subject: Any?) -> Any> = arrayListOf()

    var operations: MutableList<Operation> = arrayListOf()

    enum class Action { AddOperation, RemoveOperation, Filter }

    fun addOperation(operation: Operation) {
        operations.add(0, operation)
        notifyObservers(Action.AddOperation, operation)
    }

    fun removeOperation(operation: Operation) {
        operations.remove(operation)
        notifyObservers(Action.RemoveOperation, operation)
    }

    fun filter(from: BigDecimal?, to: BigDecimal?) {
        val fromValue = from ?: BigDecimal(0)
        val toValue = to ?: BigDecimal(0)
        operations.filter {
            if (BigDecimal(0) < fromValue && it.value.amount < fromValue) false
            if (BigDecimal(0) < toValue   && it.value.amount > toValue)   false
            true
        }
        notifyObservers(Action.Filter)
    }

    fun registerObserver(callback: (action: Action, subject: Any?) -> Any) {
        callbacks.add(callback)
    }

    fun removeObserver(callback: (action: Action, subject: Any?) -> Any) {
        if (callbacks.indexOf(callback) >= 0) {
            callbacks.remove(callback)
        }
    }

    fun notifyObservers(action: Action, subject: Any?) {
        for (callback in callbacks) {
            callback(action, subject)
        }
    }

    fun notifyObservers(action: Action) {
        notifyObservers(action, null)
    }
}