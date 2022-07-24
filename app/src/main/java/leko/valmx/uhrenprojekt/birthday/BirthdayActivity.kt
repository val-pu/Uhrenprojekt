package leko.valmx.uhrenprojekt.birthday

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_birthday.*
import leko.valmx.uhrenprojekt.R
import java.lang.reflect.Type

class BirthdayActivity : AppCompatActivity() {

    lateinit var birthdays: ArrayList<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)

        init()
    }

    fun init(){
        val prefs = getSharedPreferences("WortUhr", MODE_PRIVATE)
        val firstExe = prefs.getBoolean("first_execution_birthdays", true)
        if(firstExe){
            birthdays = ArrayList<Array<String>>()
            saveArrayList(birthdays, "birthdays")
            prefs.edit().putBoolean("first_execution_birthdays", false).apply()
        }
        else{
            birthdays = getArrayList("birthdays")
        }

        val birthdayRecyclerAdapter = BirthdayAdapter(birthdays)
        calendar_recycler.adapter = birthdayRecyclerAdapter
        calendar_recycler.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBirthday(view: View){
        birthdays.add(arrayOf("person", "datum"))
        calendar_recycler.adapter?.notifyDataSetChanged()
        saveArrayList(birthdays, "birthdays")
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