package com.brandoncano.resistancecalculator.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.setupActionBar

/**
 * Job: Activity for about page of the app.
 */
@Suppress("UNUSED_PARAMETER")
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupActionBar(R.string.about)
        makeRandomResistorImage()
    }

    fun easterEgg(view: View) = makeRandomResistorImage()

    fun rateApp(view: View) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun viewCapacitorApp(view: View) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.capacitorcalculator")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun makeRandomResistorImage() {
        val band1: ImageView = findViewById(R.id.r_p2_band1)
        val band2: ImageView = findViewById(R.id.r_p4_band2)
        val band3: ImageView = findViewById(R.id.r_p6_band3)
        val band4: ImageView = findViewById(R.id.r_p8_band4)
        val band5: ImageView = findViewById(R.id.r_p10_band5)
        val band6: ImageView = findViewById(R.id.r_p12_band6)

        setRandomColorFilter(band1)
        setRandomColorFilter(band2)
        setRandomColorFilter(band4)
        setRandomColorFilter(band5)

        when ((4..6).random()) {
            4 -> {
                setRandomColorFilter(band3, R.color.resistor_blank)
                setRandomColorFilter(band6, R.color.resistor_blank)
            } 5 -> {
                setRandomColorFilter(band3)
                setRandomColorFilter(band6, R.color.resistor_blank)
            } 6 -> {
                setRandomColorFilter(band3)
                setRandomColorFilter(band6)
            }
        }
    }

    private fun setRandomColorFilter(band: ImageView, color: Int = ColorFinder.randomColor()) {
        band.setColorFilter(ContextCompat.getColor(this, color))
    }
}
