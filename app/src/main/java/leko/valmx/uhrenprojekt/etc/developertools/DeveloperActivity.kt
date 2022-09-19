package leko.valmx.uhrenprojekt.etc.developertools

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_developer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.util.SavedDataManager
import leko.valmx.uhrenprojekt.utils.WidgetHelper

class DeveloperActivity : UhrAppActivity(){

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

        //Blue.initRelyInterface(this)

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
            SavedDataManager.saveArrayList(console, "console", this)
            prefs.edit().putBoolean("developertools_first_open", false).apply()
        } else {
            console = SavedDataManager.getArrayList("console", this)
        }
        console_recycler.adapter = ConsoleAdapter(console)
        console_recycler.layoutManager = LinearLayoutManager(this)
        if(console.size > 0) console_recycler.smoothScrollToPosition(console.size-1)
    }

    fun sendMessage(msg: String) {
        try {
            Blue.sendCommand(msg, window.decorView.rootView)
        }catch(e: Exception){
        }
    }

    override fun onBackPressed() {
        isActive = false
        super.onBackPressed()
    }


    fun sendExecutionCommand(view: View) {

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
        SavedDataManager.saveArrayList(console, "console", this)
        console_recycler.smoothScrollToPosition(console.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearConsole(view: View) {
        console.clear()
        console_recycler.adapter?.notifyDataSetChanged()
        SavedDataManager.saveArrayList(console, "console", this)
    }

}