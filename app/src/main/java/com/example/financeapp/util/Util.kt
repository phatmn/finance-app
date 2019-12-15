package com.example.financeapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.financeapp.core.Currency
import com.example.financeapp.core.GsonAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object Util {
    val gson: Gson =
        GsonBuilder()
            .registerTypeAdapter(Currency::class.java, GsonAdapter<Currency>())
            .create()

    fun <T> saveToSharedPrefs(context: Context, name: String, obj: T) {
        PreferenceManager
            .getDefaultSharedPreferences(context.applicationContext)
            .edit()
            .also {
                it.putString(name, gson.toJson(obj))
                it.apply()
            }
    }

    inline fun <reified T> getFromSharedPrefs(context: Context, name: String): T? {
        val appSharedPrefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        val json = appSharedPrefs.getString(name, "")
        return this.gson.fromJson<T>(json, object : TypeToken<T?>() {}.type)
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

