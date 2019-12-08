package com.example.financeapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Util {
    fun <T> saveToSharedPrefs(context: Context, name: String, obj: T) {
        PreferenceManager
            .getDefaultSharedPreferences(context.applicationContext)
            .edit()
            .also {
                it.putString(name, Gson().toJson(obj))
                it.apply()
            }
    }

    fun <T> getFromSharedPrefs(context: Context, name: String): T? {
        val appSharedPrefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        val json = appSharedPrefs.getString(name, "")
        return Gson().fromJson<T>(json, object : TypeToken<T?>() {}.type)
    }
}

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

