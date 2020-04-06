package com.example.travel_taker.Login.CurrencyConverter.rest;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("currencymobile")
    Call<JsonObject> getCurrencyMobile();

}
