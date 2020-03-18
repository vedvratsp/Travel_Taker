package com.example.travel_taker.Login.CurrencyConverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.travel_taker.Login.CurrencyConverter.ui.RatesFragment
import com.example.travel_taker.Login.CurrencyConverter.viewmodel.RatesViewModel
import com.example.travel_taker.R


class CurrencyConverter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)

        // Init our ViewModel and fetch latest data
        val ratesViewModel: RatesViewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)
        ratesViewModel.refreshRates()

        displayFragmentRates()
    }

    private fun displayFragmentRates() {

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()

        fragmentTransaction.replace(R.id.mainFrameLayout, RatesFragment.newInstance())
        fragmentTransaction.commit()
    }

}

