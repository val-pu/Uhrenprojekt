package leko.valmx.uhrenprojekt.developertools

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_developer.*
import leko.valmx.uhrenprojekt.R
import java.lang.reflect.Type

class DeveloperActivity : AppCompatActivity() {

    private val ERROR: String = "ERROR"
    private val SUCCESS: String = "SUCCESS"
    private val INFORMATION: String = "INFO"
    lateinit var console: ArrayList<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        val firstOpen = prefs.getBoolean("developertools_first_open", true)

        if(firstOpen){
            console = ArrayList<Array<String>>()
            saveArrayList(console, "console")
            prefs.edit().putBoolean("developertools_first_open", false).apply()
        }
        else{
            console = getArrayList("console")
        }
        console_recycler.adapter = ConsoleAdapter(console)
        console_recycler.layoutManager = LinearLayoutManager(this)
    }

    fun sendMessage(msg: String){
        //TODO send message 'msg' to clock
    }

    fun receiveMessage(msg: String){
        if(msg == "SUCCESSFUL_EXECUTED"){
            showReply("msg successfully executed", SUCCESS)
        }

        //TODO handle other received messages
    }


    fun sendExecutionCommand(view: View){
        val msg = developertools_field_send_msg.text.toString()
        if(msg != "") {
            showReply("send message: \'$msg\'", SUCCESS)
            showReply("waiting for response", SUCCESS)
            sendMessage(msg)
        }
        else{
            showReply("No message to send", INFORMATION)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showReply(pMsg: String, type: String){
        var msg = pMsg
        if(type == INFORMATION){
            msg = "INFO: $msg"
        }
        if(type == ERROR){
            msg = "ERROR: $msg"
        }
        console.add(arrayOf(msg, type))
        console_recycler.adapter?.notifyDataSetChanged()
        saveArrayList(console, "console")
        console_recycler.smoothScrollToPosition(console.size-1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearConsole(view: View){
        console.clear()
        console_recycler.adapter?.notifyDataSetChanged()
        saveArrayList(console, "console")
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