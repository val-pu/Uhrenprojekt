package leko.valmx.uhrenprojekt.developertools

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_developer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.newP.CustomizerActivity
import leko.valmx.uhrenprojekt.newP.autoconnect.UhrAppActivity
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper
import java.lang.reflect.Type

class DeveloperActivity : UhrAppActivity(), ReplyCallInterface{

    lateinit var console: ArrayList<Array<String>>
    val ERROR: String = "ERROR"
    val SUCCESS: String = "SUCCESS"
    val INFORMATION: String = "INFO"
    val CONNECTION_INFO: String = "CONNECTION_INFO"

    companion object{
        var isActive = false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)
        isActive = true

        developertools_connection_btn.setOnClickListener {
            val name_id: String = "NameID: " + Blue.NAME_ID
            val charac: String = "Characteristics: 0000FFE1-0000-1000-8000-00805F9B34FB"
            val macadress: String = "MAC-Address: " + getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).getString(
                Blue.NAME_ID, ""
            ).toString()
            val connected: String = "Connnected: " + Blue.connection!!.isActive.toString()
            val verbouse: String = "Verbouse: " + Blue.connection!!.verbose.toString()
            val rsii: String = "rsii: " + Blue.connection!!.rsii.toString()

            val finalInfo: String = "\n$name_id\n$macadress\n$connected\n" + charac + "\n$verbouse\n$rsii"
            showReply(finalInfo, CONNECTION_INFO)
        }

        val prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        val firstOpen = prefs.getBoolean("developertools_first_open", true)

        if (firstOpen) {
            console = ArrayList<Array<String>>()
            saveArrayList(console, "console")
            prefs.edit().putBoolean("developertools_first_open", false).apply()
        } else {
            console = getArrayList("console")
        }
        console_recycler.adapter = ConsoleAdapter(console)
        console_recycler.layoutManager = LinearLayoutManager(this)
        if(console.size > 0) console_recycler.smoothScrollToPosition(console.size-1)
    }

    fun sendMessage(msg: String) {
        Blue.sendCommand(msg, getWindow().getDecorView().getRootView())
    }

    fun receiveMessage(msg: String) {
        if (msg == "SUCCESSFUL_EXECUTED") {
            showReply("msg successfully executed", SUCCESS)
        }



        //TODO handle other received messages
    }

    override fun onBackPressed() {
        isActive = false
        super.onBackPressed()
    }


    fun sendExecutionCommand(view: View) {
        Blue.initRelyInterface(this)
        val msg = developertools_field_send_msg.text.toString()
        if (msg != "") {
            showReply("send message: \'$msg\'", SUCCESS)
            showReply("waiting for response", SUCCESS)
            sendMessage(msg)
        } else {
            showReply("No message to send", INFORMATION)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showReply(pMsg: String, type: String, show: Boolean = false) {
        var msg = pMsg
        if (type == INFORMATION) {
            msg = "$INFORMATION: $msg"
        }
        if (type == ERROR) {
            msg = "$ERROR: $msg"
        }
        if(type == SUCCESS && show){
            msg = "$SUCCESS: $msg"
        }
        console.add(arrayOf(msg, type))
        console_recycler.adapter?.notifyDataSetChanged()
        saveArrayList(console, "console")
        console_recycler.smoothScrollToPosition(console.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearConsole(view: View) {
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



    override fun callReply(success: Int) {
        if(success == 1){
            showReply("send successfully", SUCCESS, true)
        }else{
            showReply("sending failed.\n" +
                    "No Connection established", ERROR)
        }
    }
}