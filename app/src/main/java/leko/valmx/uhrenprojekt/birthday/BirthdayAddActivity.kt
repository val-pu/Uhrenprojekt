package leko.valmx.uhrenprojekt.birthday

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_birthday_add.*
import leko.valmx.uhrenprojekt.R
import java.lang.reflect.Type

class BirthdayAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_add)
    }

    fun addBirthdayStuff(view: View){
        val name = birthday_add_name.text.toString()
        var day = birthday_add_date_day.text.toString()
        var month = birthday_add_date_month.text.toString()
        if(name != "" && day != "" && month != "") {
            val dayNumber = day.toInt()
            val monthNumber = month.toInt()
            val dayRange = 1..31
            val monthRange = 1..12
            if(day.length == 1) day = "0$day"
            if(month.length == 1) month = "0$month"

            if(dayNumber in dayRange && monthNumber in monthRange) {
                if(day.length > 2) day = day[day.length-2].toString() + day[day.length-1].toString()
                if(month.length > 2) month = month[month.length-2].toString() + month[month.length-1].toString()
                val date = "$day.$month"
                var list: ArrayList<Array<String>> = getArrayList("birthdays")
                list.add(arrayOf(name, date))
                saveArrayList(list, "birthdays")
                startActivity(Intent(this, BirthdayActivity::class.java))
            }
            else{
                Toast.makeText(this, "Du Scherzkeks :)", Toast.LENGTH_SHORT).show()
                birthday_add_date_day.setText("")
                birthday_add_date_month.setText("")
            }
        }
        else{
            Toast.makeText(this, "Bitte fülle zunächst alle Felder aus!", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveArrayList(list: java.util.ArrayList<Array<String>>?, key: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?): ArrayList<Array<String>> {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val gson = Gson()
        val json: String? = prefs.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<Array<String>>>() {}.getType()
        return gson.fromJson(json, type)
    }
}