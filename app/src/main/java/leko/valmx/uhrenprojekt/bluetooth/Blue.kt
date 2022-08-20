package leko.valmx.uhrenprojekt.bluetooth

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import quevedo.soares.leandro.blemadeeasy.BLE

object Blue {
    lateinit var ble: BLE

    fun sendCommand(command: String, view: View? = null) {

        if (view != null) Snackbar.make(view, "Sende Befehl >$command", Snackbar.LENGTH_SHORT)
            .show()

    }

}