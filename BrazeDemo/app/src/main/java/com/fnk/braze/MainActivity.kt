package com.fnk.braze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.braze.Braze
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logPurchase()
    }

    private fun logPurchase() {
        Braze.getInstance(this).logPurchase(
            productId= "",
            currencyCode= "String",
            price= BigDecimal("0.04"),
            quantity= 8
        )
    }
}