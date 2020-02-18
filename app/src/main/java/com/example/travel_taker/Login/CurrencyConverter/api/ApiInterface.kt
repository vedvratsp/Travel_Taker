package com.example.travel_taker.Login.CurrencyConverter.api

import com.example.travel_taker.Login.CurrencyConverter.api.model.LatestRates
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vedvrat
 */
interface ApiInterface {

    @GET("latest")
    fun getLatestRates(@Query("base") baseRate: String): Call<LatestRates>

}