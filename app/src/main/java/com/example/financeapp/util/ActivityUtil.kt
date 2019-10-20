package com.example.financeapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int): Boolean {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(frameId, fragment, fragment.javaClass.simpleName)
    transaction.commit()
    return true
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int): Boolean {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(frameId, fragment, fragment.javaClass.simpleName)
    transaction.commit()
    return true
}