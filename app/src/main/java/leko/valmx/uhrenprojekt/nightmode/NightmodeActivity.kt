package leko.valmx.uhrenprojekt.nightmode

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nightmode.*
import leko.valmx.uhrenprojekt.R

class NightmodeActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nightmode)

        prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        val beginHour = prefs.getString("nightmode_begin_hour", "")
        val beginMinute = prefs.getString("nightmode_begin_minute", "")
        val endHour = prefs.getString("nightmode_end_hour", "")
        val endMinute = prefs.getString("nightmode_end_minute", "")
        val brightness = prefs.getString("nightmode_brightness", "")

        nightmode_begintime_hour.setText(beginHour)
        nightmode_begintime_minute.setText(beginMinute)
        nightmode_endtime_hour.setText(endHour)
        nightmode_endtime_minute.setText(endMinute)
        nightmode_brightness_field.setText(brightness)
    }

    fun backToCalendarActivityfromNightmode(view: View){
        finish()
    }

    fun changeNightmodeSettings(view: View){
        var beginHour = nightmode_begintime_hour.text.toString()
        var beginMinute = nightmode_begintime_minute.text.toString()
        var endHour = nightmode_endtime_hour.text.toString()
        var endMinute = nightmode_endtime_minute.text.toString()
        var brightness = nightmode_brightness_field.text.toString()

        val filled: Boolean = beginHour != "" && beginMinute != "" && endHour != "" && endMinute != ""
                && brightness != ""

        if(filled){
            if(beginHour.length == 1) beginHour = "0$beginHour"
            if(beginMinute.length == 1) beginMinute = "0$beginMinute"
            if(endHour.length == 1) endHour = "0$endHour"
            if(endMinute.length == 1) endMinute = "0$endMinute"

            val acceptableTimeRange: Boolean = beginHour.toInt() in 0..23 && endHour.toInt() in 0..23
                    && beginMinute.toInt() in 0..59 && endMinute.toInt() in 0..59

            val acceptableBrightnessRange: Boolean = brightness.toInt() in 0..255

            if(acceptableTimeRange){
                if(acceptableBrightnessRange){
                    if(beginHour.length > 2) beginHour = beginHour[beginHour.length-2].toString() +
                            beginHour[beginHour.length-1].toString()
                    if(beginMinute.length > 2) beginMinute = beginMinute[beginMinute.length-2].toString() +
                            beginMinute[beginMinute.length-1].toString()
                    if(endHour.length > 2) endHour = endHour[endHour.length-2].toString() +
                            endHour[endHour.length-1].toString()
                    if(endMinute.length > 2) endMinute = endMinute[endMinute.length-2].toString() +
                            endMinute[endMinute.length-1].toString()
                    if(brightness.length > 3) brightness = brightness[brightness.length-3].toString() +
                            brightness[brightness.length-2].toString() + brightness[brightness.length-1].toString()

                    while(brightness.startsWith('0') && brightness != "0")
                        brightness = brightness.substring(1, brightness.length)

                    prefs.edit().putString("nightmode_begin_hour", beginHour).apply()
                    prefs.edit().putString("nightmode_begin_minute", beginMinute).apply()
                    prefs.edit().putString("nightmode_end_hour", endHour).apply()
                    prefs.edit().putString("nightmode_end_minute", endMinute).apply()
                    prefs.edit().putString("nightmode_brightness", brightness).apply()

                    nightmode_begintime_hour.setText(beginHour)
                    nightmode_begintime_minute.setText(beginMinute)
                    nightmode_endtime_hour.setText(endHour)
                    nightmode_endtime_minute.setText(endMinute)
                    nightmode_brightness_field.setText(brightness)

                    val confirmationMessage =
                        "$beginHour:$beginMinute Uhr - $endHour:$endMinute Uhr \n" +
                                "helligkeit: $brightness"
                    Toast.makeText(this, confirmationMessage, Toast.LENGTH_SHORT).show()
                }
                else{
                    val errorMessage: String = "Die Helligkeit muss zwischen 0 und 255 liegen!"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Du Scherzkeks :)", Toast.LENGTH_SHORT).show()
            }

        }
    }
}