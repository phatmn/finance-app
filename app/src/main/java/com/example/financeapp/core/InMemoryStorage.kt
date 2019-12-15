package com.example.financeapp.core

import com.example.financeapp.operation.Operation

object InMemoryStorage{
    private var callbacks: MutableList<(action: Action, subject: Any) -> Any> = arrayListOf()

    var operations: MutableList<Operation> = arrayListOf()

    enum class Action { AddOperation, RemoveOperation }

    fun addOperation(operation: Operation) {
        operations.add(0, operation)
        notifyObservers(Action.AddOperation, operation)
    }

    fun removeOperation(operation: Operation) {
        operations.remove(operation)
        notifyObservers(Action.RemoveOperation, operation)
    }

    fun registerObserver(callback: (action: Action, subject: Any) -> Any) {
        callbacks.add(callback)
    }

    fun removeObserver(callback: (action: Action, subject: Any) -> Any) {
        if (callbacks.indexOf(callback) >= 0) {
            callbacks.remove(callback)
        }
    }

    fun notifyObservers(action: Action, subject: Any) {
        for (callback in callbacks) {
            callback(action, subject)
        }
    }
}