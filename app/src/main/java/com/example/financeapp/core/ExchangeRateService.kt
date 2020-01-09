package com.example.financeapp.core

import com.example.financeapp.util.Util
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

interface ExchangeRateService {
    @GET("latest")
    fun getRates(
        @Query("base")
        currency: String
    ):
//            Observable<ExchangeRateModel.Result>
            Call<ExchangeRateModel.Result>

    companion object {
        fun create(): ExchangeRateService {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io/")
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(
                    GsonConverterFactory.create(Util.gson)
                )
//                .addCallAdapterFactory(
//                    RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ExchangeRateService::class.java)
        }

        /**
         * workaround for ssl protocol
         */
        private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts: Array<TrustManager> = arrayOf(
                    object : X509TrustManager {
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

                // Install the all-trusting trust manager
                val sslContext: SSLContext = SSLContext
                    .getInstance("SSL")
                    .also {
                        it.init(null, trustAllCerts, SecureRandom())
                    }

                OkHttpClient.Builder()
                    .sslSocketFactory(
                        sslContext.socketFactory,
                        trustAllCerts[0] as X509TrustManager
                    )
                    .hostnameVerifier { _, _ -> true }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
    }
}
