package com.example.travel_taker.Login.CurrencyConverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.travel_taker.Login.CurrencyConverter.api.ApiClient
import com.example.travel_taker.Login.CurrencyConverter.api.model.LatestRates
import com.example.travel_taker.Login.CurrencyConverter.viewmodel.model.CurrencyRate

/**
 * Created by vedvrat
 */
class RatesViewModel : ViewModel() {

    private val RATE_UPDATE: Any = Object()

    // Default values
    private var baseCurrency: String = "EUR"
    private var baseValue: Float = 100F

    private val latestRatesObs: Observer<LatestRates>
    private val currencyRates: MutableLiveData<List<CurrencyRate>> = MutableLiveData()

    /**
     * Default Constructor
     *
     * Setup LatestRates Observer from ApiClient
     *
     */
    init {

        latestRatesObs = Observer {
            if (it != null) updateLatestRates(it)
        }

        ApiClient.getLatestRates().observeForever(latestRatesObs)
    }

    /**
     * Stop observing currencyRates when ViewModel is destroyed
     */
    override fun onCleared() {
        ApiClient.getLatestRates().removeObserver(latestRatesObs)
    }

    /**
     * Converts the latest data to a List<CurrencyRate>
     *
     * @param latestRates Latest Rates received from ApiClient.
     */
    private fun updateLatestRates(latestRates: LatestRates) {

        val newCurrencyRates: MutableList<CurrencyRate> = mutableListOf<CurrencyRate>()

        synchronized(RATE_UPDATE) {

            newCurrencyRates.add(CurrencyRate(latestRates.base!!, 1.0F, baseValue))

            latestRates.rates?.forEach { (currency, rate) ->
                newCurrencyRates.add(CurrencyRate(currency, rate, rate*baseValue))
            }

            currencyRates.value = newCurrencyRates
        }
    }

    fun getCurrencyRates() : LiveData<List<CurrencyRate>> = currencyRates

    fun refreshRates() {
        ApiClient.refreshLatestRates(baseCurrency)
    }

    fun setNewBase(newBaseCurrency: String, newBaseValue: Float) {

        // Ignore if same
        if (baseCurrency.equals(newBaseCurrency))
            return

        baseCurrency = newBaseCurrency
        baseValue = newBaseValue
        refreshRates()
    }

    fun setNewBaseValue(value: Float) {
        // Ignore so it doesn't enter an infinite loop
        if (baseValue.equals(value))
            return

        synchronized(RATE_UPDATE) {
            baseValue = value

            val newCurrencyRates: MutableList<CurrencyRate> = mutableListOf<CurrencyRate>()

            currencyRates.value?.forEach { newCurrencyRates.add(CurrencyRate(it.currency,it.rate,it.rate*baseValue))}
            currencyRates.value = newCurrencyRates
        }
    }
}
