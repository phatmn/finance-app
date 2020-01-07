package com.example.financeapp.analytics

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.financeapp.BaseFragment
import com.example.financeapp.R
import com.example.financeapp.core.*
import com.example.financeapp.util.reverse
import com.example.financeapp.util.round
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_analytics.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class AnalyticsFragment : BaseFragment() {

    private val handler = Handler()
    private var rates: Map<String, BigDecimal> = emptyMap()
    private var disposable: Disposable? = null
    private val exchangeRateSrv by lazy {
        ExchangeRateService.create()
    }

    companion object {
        fun newInstance(): AnalyticsFragment {
            return AnalyticsFragment()
        }
    }

    override fun layoutId(): Int = com.example.financeapp.R.layout.fragment_analytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler.postDelayed(
            {
                progress.hide()
                analyticsView.show()

                Glide.with(this)
                    .load("https://github.com/bumptech/glide/raw/master/static/glide_logo.png")
                    .into(image)
            },
            2000
        )

        // todo default currency
        getRates(RUB)
    }

    private fun getRates(baseCurrency: Currency) {
//        disposable = exchangeRateSrv.getRates(baseCurrency.toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { result ->
//                        this.rates = result.rates
//                        Log.d("debuglog", this.rates.toString())
//                    },
//                    { error ->
//                        Toast
//                            .makeText(this.requireContext(), error.message, Toast.LENGTH_LONG)
//                            .show()
//                    }
//                )

        val call = exchangeRateSrv.getRates(baseCurrency.code)

        call.enqueue(object : Callback<ExchangeRateModel.Result> {
            override fun onResponse(
                call: Call<ExchangeRateModel.Result>,
                response: Response<ExchangeRateModel.Result>
            ) {
                if (response.code() == 200 && response.body() != null) {
                    rates = response.body()!!.rates
                    val usdRate: Double = rates[USD.code]?.toDouble() ?: 0.0
                    val eurRate: Double = rates[EUR.code]?.toDouble() ?: 0.0
                    currencyRates.text = resources.getString(
                        R.string.currencyRates,
                        USD.code, usdRate.reverse().round (2),
                        EUR.code, eurRate.reverse().round (2)
                    )
                }
            }

            override fun onFailure(call: Call<ExchangeRateModel.Result>, t: Throwable) {
                Toast
                    .makeText(requireContext(), t.message, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()
        handler.removeCallbacksAndMessages(null)
    }
}
