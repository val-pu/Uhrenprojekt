package leko.valmx.uhrenprojekt.bluetooth

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.View
import com.google.android.material.snackbar.Snackbar
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.BluetoothConnection

object Blue {
    lateinit var ble: BLE
    var connection : BluetoothConnection? = null

    val debug = true

    fun sendCommand(command: String, view: View? = null) {

        if (view != null) Snackbar.make(view, "Sende Befehl >$command", Snackbar.LENGTH_SHORT)
            .show()

    }

    private val NAME_ID = "DEVICE_ID"

    fun getDeviceName(ctx: Context): String? =
        ctx.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).getString(
            NAME_ID, "LE-Bose QC35 II"
        )

}