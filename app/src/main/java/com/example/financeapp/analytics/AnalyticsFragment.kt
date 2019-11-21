package com.example.financeapp.analytics

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.example.financeapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_analytics.*
import kotlinx.android.synthetic.main.progress_bar.*

class AnalyticsFragment : BaseFragment() {

    companion object {
        fun newInstance() : AnalyticsFragment {
            return AnalyticsFragment()
        }
    }

    override fun layoutId(): Int = com.example.financeapp.R.layout.fragment_analytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().also {
            it.postDelayed({
                progress.visibility = View.INVISIBLE
                analyticsView.visibility = View.VISIBLE

                Glide.with(this)
                    .load("https://github.com/bumptech/glide/raw/master/static/glide_logo.png")
                    .into(image)
            }, 2000)
        }
    }
}
