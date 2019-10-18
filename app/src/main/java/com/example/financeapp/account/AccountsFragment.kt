package com.example.financeapp.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.financeapp.R
import kotlinx.android.synthetic.main.fragment_accounts.*

class AccountsFragment : Fragment() {

    companion object {
        fun newInstance() : AccountsFragment {
            return AccountsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_accounts, container, false)


        return view
    }
}
