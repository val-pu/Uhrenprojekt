package leko.valmx.uhrenprojekt.autoconnect

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.sheet_bluetooth_autoconnect.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.IntroActivity
import leko.valmx.uhrenprojekt.SimpleControlActivity
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import leko.valmx.uhrenprojekt.utils.WidgetHelper
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.models.BLEDevice

class ConnectBottomSheet(val activity: UhrAppActivity) : Sheet() {

    companion object {
        var connectBottomSheet: ConnectBottomSheet? = null

        fun getInstance(activity: UhrAppActivity): ConnectBottomSheet {
            if (connectBottomSheet == null) {
                connectBottomSheet = ConnectBottomSheet(activity)
            }
            return connectBottomSheet!!
        }

        fun undo() {
            connectBottomSheet = null
        }
    }


    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(activity).inflate(R.layout.sheet_bluetooth_autoconnect, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDialog()!!.setCanceledOnTouchOutside(false);
        setCancelable(false)

        if (Blue.debug) {
            dismiss()
            return
        }

        if (Blue.connection != null && Blue.connection!!.isActive) dismiss()


        feedBack.startFeedBack(Integer.MAX_VALUE)
        startSearch()


    }

    fun show(
        ctx: Context,
        width: Int? = null,
        func: ConnectBottomSheet.() -> Unit = {},
    ): ConnectBottomSheet {
        this.windowContext = ctx
        this.width = width
//        UhrAppActivity.isSheetDisplayed = true
        positiveListener = {
            dismiss()
            getInstance(activity).show(ctx, width, func)
        }
        positiveText = "Suche Wiederholen"

        onNegative("Verbinde mit anderem Gerät") {

            ctx.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).edit()
                .putString(Blue.NAME_ID, "").apply()

            startActivity(Intent(ctx, IntroActivity::class.java))


        }

//        displayNegativeButton(false)


        title("Autoverbindung")


        func(this)

        this.show()
        return this
    }

    @SuppressLint("MissingPermission")
    @OptIn(DelicateCoroutinesApi::class)
    fun startSearch() {
        val ble = activity.ble
        try {

            GlobalScope.launch {
                try {
                    val address = Blue.getDeviceName(requireContext())


                    text_status.text = "Verbinde mit $address"
                    Log.i("Bluetooth","Starte Verbindungsprozess...")
                    UhrAppActivity.connection = ble.scanFor(macAddress = address, settings = ScanSettings.Builder().setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE).build())
                    Log.i("Bluetooth","Beende Verbindungsprozess...")

                    val connection = UhrAppActivity.connection


                    if (!connection!!.isActive) {
                        text_status.text = "Suche fehlgeschlagen"
                        return@launch
                    }
                    if (text_status != null) text_status.text =
                        "Gerät Gefunden - Verbunden ${connection.isActive}"
                    connection?.write("0000FFE1-0000-1000-8000-00805F9B34FB", "matrix")!!

                    dismiss()
                } catch (e2: IllegalStateException) {
                    dismiss()
                }
            }
        } catch (e: Exception) {
        }


    }
}