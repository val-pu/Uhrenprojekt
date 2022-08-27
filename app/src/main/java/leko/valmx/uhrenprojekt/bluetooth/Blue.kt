package leko.valmx.uhrenprojekt.bluetooth

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.View
import com.google.android.material.snackbar.Snackbar
import leko.valmx.uhrenprojekt.bluetooth.Blue.callReply
import leko.valmx.uhrenprojekt.developertools.DeveloperActivity
import leko.valmx.uhrenprojekt.developertools.ReplyCallInterface
import leko.valmx.uhrenprojekt.newP.autoconnect.ConnectBottomSheet
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.BluetoothConnection

object Blue: ReplyCallInterface{
    lateinit var ble: BLE
    var connection : BluetoothConnection? = null

    var reply: ReplyCallInterface = this as ReplyCallInterface

    val debug = false
    var success: Int = 0

    var isConnected = false

    fun sendCommand(command: String, view: View? = null) {

        val write = connection?.write("0000FFE1-0000-1000-8000-00805F9B34FB", command)!!

        view?.apply {
            if(!write && !DeveloperActivity.isActive) ConnectBottomSheet().show(view.context) { }
            if(write) success = 1 else success = -1
            reply.callReply(success)
        }


    }

    val NAME_ID = "DEVICE_ID"

    fun getDeviceName(ctx: Context): String? =
        ctx.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).getString(
            NAME_ID, ""
        )

    fun initRelyInterface(replyInterface: ReplyCallInterface){
        reply = replyInterface
    }

    override fun callReply(success: Int) {
    }

}