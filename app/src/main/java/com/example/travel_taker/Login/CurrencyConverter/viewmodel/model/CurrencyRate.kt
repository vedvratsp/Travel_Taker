package com.example.travel_taker.Login.CurrencyConverter.viewmodel.model

/**
 * Created vedvrat
 *
 * Declare as Data Class so it reduces boilerplate
 * when comparing items
 */
data class CurrencyRate (
    val currency: String,
    val rate: Float,
    var value : Float
)