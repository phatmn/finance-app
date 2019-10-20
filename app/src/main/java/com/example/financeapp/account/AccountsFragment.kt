package com.example.financeapp.account

import com.example.financeapp.BaseFragment
import com.example.financeapp.R

class AccountsFragment : BaseFragment() {

    companion object {
        fun newInstance() : AccountsFragment {
            return AccountsFragment()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_accounts

}
