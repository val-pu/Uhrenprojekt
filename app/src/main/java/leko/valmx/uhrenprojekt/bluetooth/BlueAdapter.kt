package leko.valmx.uhrenprojekt.bluetooth

import android.view.View
import androidx.appcompat.app.AppCompatActivity

object BlueAdapter: AppCompatActivity() {
    fun sendCommand(msg: String){
        var view: View = View(this)
        Blue.sendCommand(msg, view)
    }
}