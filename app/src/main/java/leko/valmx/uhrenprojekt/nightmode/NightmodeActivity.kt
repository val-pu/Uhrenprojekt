package leko.valmx.uhrenprojekt.nightmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import leko.valmx.uhrenprojekt.R

class NightmodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nightmode)
    }

    fun backToCalendarActivityfromNightmode(view: View){
        finish()
    }
}