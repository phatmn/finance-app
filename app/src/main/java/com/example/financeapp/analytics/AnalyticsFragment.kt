package com.example.financeapp.analytics

import com.example.financeapp.BaseFragment
import com.example.financeapp.R

class AnalyticsFragment : BaseFragment() {

    companion object {
        fun newInstance() : AnalyticsFragment {
            return AnalyticsFragment()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_analytics

}
