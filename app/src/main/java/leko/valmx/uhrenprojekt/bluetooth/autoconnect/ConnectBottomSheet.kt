package leko.valmx.uhrenprojekt.bluetooth.autoconnect

import android.annotation.SuppressLint
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.sheet_bluetooth_autoconnect.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.IntroActivity
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import leko.valmx.uhrenprojekt.widgets.WidgetHelper

class ConnectBottomSheet(val activity: UhrAppActivity) : Sheet() {

    private val searchTimeout = 30000L

    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(activity).inflate(R.layout.sheet_bluetooth_autoconnect, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog!!.setCanceledOnTouchOutside(false);
        isCancelable = false

        if (Blue.debug) {
            dismiss()
            return
        }

        if (UhrAppActivity.connection != null && UhrAppActivity.connection!!.isActive) {
            dismiss()
            return
        }

        feedBack.startFeedBack(Integer.MAX_VALUE)

        startSearch()
    }

    fun show(
        ctx: Context,
        width: Int? = null,
    ) {
        this.windowContext = ctx
        this.width = width
        return


        title(getString(R.string.blue_autoconnect_sheet_title))

        // Rufe das Sheet noch ein mal auf, falls die Suche neugestartet werden soll

        positiveText = getString(R.string.blue_autoconnect_retry)
        positiveListener = {
            dismiss()
            ConnectBottomSheet(activity).show(ctx, width)
        }

        // Lösche gespeicherte MAC-Adresse, falls der User sich mit einer anderen Uhr verbinden will und sende ihn/ sie zurück ins Intro
        // TODO füge kurze Abfrage ein, ob der User sich nicht verclickt hat

        onNegative(getString(R.string.blue_autoconnect_connect_to_other)) {
            ctx.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).edit()
                .putString(Blue.NAME_ID, "").apply()
            startActivity(Intent(ctx, IntroActivity::class.java))
        }

        this.show()

    }

    /**
     * Suche/ Verbinde nach der Uhr und handle feedback
     */

    @SuppressLint("MissingPermission", "SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    fun startSearch() {

        val ble = activity.ble
        try {

            GlobalScope.launch {

                val address = Blue.getDeviceName(requireContext())

                text_status.text =
                    getString(R.string.blue_autoconnect_feedback_connecting_to) + address

                Log.i("Bluetooth", "Starte Verbindungsprozess...")

                UhrAppActivity.connection = ble.scanFor(
                    macAddress = address,
                    timeout = searchTimeout,
                    settings = ScanSettings.Builder().setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE).build()
                )

                Log.i("Bluetooth", "Beende Verbindungsprozess... mit Verbindung: ${UhrAppActivity.connection}")

                val connection = UhrAppActivity.connection

                if (!connection!!.isActive) {
                    text_status.text = getString(R.string.blue_autoconnnect_feedback_fail)
                    return@launch
                }

                dismiss()

            }
        } catch (_: Exception) {
        }


    }
}