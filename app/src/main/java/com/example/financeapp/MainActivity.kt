package com.example.financeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.financeapp.account.AccountsFragment
import com.example.financeapp.operation.OperationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            replace(OperationsFragment.newInstance())
        }
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.menu_ops -> return@OnNavigationItemSelectedListener replace(OperationsFragment.newInstance())
            R.id.menu_accounts -> return@OnNavigationItemSelectedListener replace(AccountsFragment.newInstance())
//            R.id.menu_analytics -> return@OnNavigationItemSelectedListener replace(AnalyticsFragment.newInstance())
        }
        false
    }

    private fun replace(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment, fragment.javaClass.simpleName)
            .commit()
        return true
    }
}
