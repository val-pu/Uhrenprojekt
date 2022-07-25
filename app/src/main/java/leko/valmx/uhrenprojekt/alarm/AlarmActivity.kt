package leko.valmx.uhrenprojekt.alarm

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_alarm.*
import leko.valmx.uhrenprojekt.R

class AlarmActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        val hour = prefs.getString("alarm_hour", "")
        val minute = prefs.getString("alarm_minute", "")
        val duration = prefs.getString("alarm_duration", "")

        alarm_hour_field.setText(hour)
        alarm_minute_field.setText(minute)
        alarm_duration_time.setText(duration)



    }

    fun backToCalendarActivityfromAlarm(view: View){
        finish()
    }

    fun changeAlarm(view: View){
        if(alarm_hour_field.text.toString() != "" && alarm_minute_field.text.toString() != "") {
            var hourStr = alarm_hour_field.text.toString()
            var minStr = alarm_minute_field.text.toString()
            if(hourStr.length == 1) hourStr = "0$hourStr"
            if(minStr.length == 1) minStr = "0$minStr"
            val hour = alarm_hour_field.text.toString().toInt()
            val minute = alarm_minute_field.text.toString().toInt()
            if (hour in 0..23 && minute in 0..59) {
                prefs.edit().putString("alarm_hour", hourStr).apply()
                prefs.edit().putString("alarm_minute", minStr).apply()
                prefs.edit().putString("alarm_duration", alarm_duration_time.text.toString()).apply()
                alarm_hour_field.setText(hourStr)
                alarm_minute_field.setText(minStr)
                Toast.makeText(this, "Alarm auf $hourStr:$minStr Uhr eingestellt (Dauer: " +
                        alarm_duration_time.text.toString() + " Sekunden)", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Du Scherzkeks :)", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Bitte fülle zunächst alle Felder aus", Toast.LENGTH_SHORT).show()
        }
    }

    fun alarmClearHour(view: View){
        alarm_hour_field.setText("")
    }

    fun alarmClearMinute(view: View){
        alarm_minute_field.setText("")
    }

    fun alarmClearDuration(view: View){
        alarm_duration_time.setText("")
    }
}