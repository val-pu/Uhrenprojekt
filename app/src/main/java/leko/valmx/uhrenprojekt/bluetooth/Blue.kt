package leko.valmx.uhrenprojekt.bluetooth

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.View
import com.google.android.material.snackbar.Snackbar
import leko.valmx.uhrenprojekt.newP.autoconnect.ConnectBottomSheet
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.BluetoothConnection

object Blue {
    lateinit var ble: BLE
    var connection : BluetoothConnection? = null

    val debug = false

    fun sendCommand(command: String, view: View? = null) {

        if (view != null) Snackbar.make(view, "Sende Befehl >$command", Snackbar.LENGTH_SHORT)
            .show()

        val write = connection?.write("0000FFE1-0000-1000-8000-00805F9B34FB", command)!!

        view?.apply {
            if(!write) ConnectBottomSheet().show(view.context) { }
        }


    }

    val NAME_ID = "DEVICE_ID"

    fun getDeviceName(ctx: Context): String? =
        ctx.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).getString(
            NAME_ID, ""
        )

}