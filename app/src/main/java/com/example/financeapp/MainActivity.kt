package com.example.financeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.account.AccountsFragment
import com.example.financeapp.operation.OperationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            val fragment = OperationsFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.menu_ops -> {
                val fragment = OperationsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_accounts -> {
                val fragment = AccountsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
//            R.id.menu_analytics -> {
//                val fragment = AnalyticsFragment()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame_layout, fragment, fragment.javaClass.getSimpleName())
//                    .commit()
//                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }}
