package com.example.financeapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.account.AccountsFragment
import com.example.financeapp.analytics.AnalyticsFragment
import com.example.financeapp.operation.OperationsFilterFragment
import com.example.financeapp.operation.OperationsFragment
import com.example.financeapp.util.addFragment
import com.example.financeapp.util.replaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val frameId: Int = R.id.frameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            addFragment(OperationsFragment.newInstance(), frameId)
        }
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_ops -> return@OnNavigationItemSelectedListener replaceFragment(
                    OperationsFragment.newInstance(),
                    frameId
                )
                R.id.menu_accounts -> return@OnNavigationItemSelectedListener replaceFragment(
                    AccountsFragment.newInstance(),
                    frameId
                )
                R.id.menu_analytics -> return@OnNavigationItemSelectedListener replaceFragment(
                    AnalyticsFragment.newInstance(),
                    frameId
                )
            }
            false
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "filter" -> {
                OperationsFilterFragment
                    .newInstance()
                    .show(supportFragmentManager,"filter")

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
