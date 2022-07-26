package leko.valmx.uhrenprojekt.developertools

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_developer.*
import leko.valmx.uhrenprojekt.R

class DeveloperActivity : AppCompatActivity() {

    private val ERROR: Int = -1
    private val SUCCESS: Int = 0
    private val INFORMATION: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        val currOutput = prefs.getString("developertools_output", "")
        developertools_reply.text = currOutput

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
            showReply("send message: \'$msg\'\n" +
                    "waiting for response...", SUCCESS)
            sendMessage(msg)
        }
        else{
            showReply("No message to send", INFORMATION)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showReply(pReply: String, success: Int){
        val prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        val currOutput = prefs.getString("developertools_output", "")
        var reply = pReply
        if(success == ERROR){
            reply = "ERROR: $reply"
        }
        if(success == SUCCESS){
        }
        if(success == INFORMATION){
            reply = "INFO: $reply"
        }
        var newOutput = currOutput
        if(newOutput != ""){
            newOutput = newOutput + "\n" + reply
        }
        else{
            newOutput = reply
        }
        developertools_reply.text = newOutput
        prefs.edit().putString("developertools_output", newOutput).apply()
    }

    fun clearConsole(view: View){
        val prefs = getSharedPreferences("Wort-Uhr", MODE_PRIVATE)
        prefs.edit().putString("developertools_output", "").apply()
        developertools_reply.text = ""
    }
}