package leko.valmx.uhrenprojekt.birthday

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_birthday.*
import leko.valmx.uhrenprojekt.MainActivity
import leko.valmx.uhrenprojekt.R
import java.lang.reflect.Type

class BirthdayActivity : AppCompatActivity(), BirthdayRecyclerInterface {

    lateinit var birthdays: ArrayList<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)
        init()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun init() {
        val prefs = getSharedPreferences("WortUhr", MODE_PRIVATE)
        val firstExe = prefs.getBoolean("first_execution_birthdays", true)
        if (firstExe) {
            birthdays = ArrayList<Array<String>>()
            saveArrayList(birthdays, "birthdays")
            prefs.edit().putBoolean("first_execution_birthdays", false).apply()
        } else {
            birthdays = getArrayList("birthdays")
        }

        if (birthdays.size == 0) {
            birthday_empty_recycler.visibility = View.VISIBLE
        } else {
            birthday_empty_recycler.visibility = View.INVISIBLE
        }

        val birthdayRecyclerAdapter = BirthdayAdapter(birthdays, this)
        calendar_recycler.adapter = birthdayRecyclerAdapter
        calendar_recycler.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBirthday(view: View) {
        saveArrayList(birthdays, "birthdays")
        startActivity(Intent(this, BirthdayAddActivity::class.java))
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

    override fun onClick(pos: Int) {
        birthdays.removeAt(pos)
        calendar_recycler.adapter?.notifyItemRemoved(pos)
        if (birthdays.size == 0) {
            birthday_empty_recycler.visibility = View.VISIBLE
        } else {
            birthday_empty_recycler.visibility = View.INVISIBLE
        }
        saveArrayList(birthdays, "birthdays")
        //TODO send update to clock
    }

    fun backToMainActivity(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun changeBirthdayAlarm(view: View) {

        //achte auf richtige Syncro mit der Uhr
        if (birthday_alarm_switch.isChecked) {
            //TODO sende 'geb' oder 'geburtstag' true
        } else {
            //TODO sende 'geb' oder 'geburtstag' false
        }
    }
}