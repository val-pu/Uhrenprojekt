package leko.valmx.uhrenprojekt.newP.autoconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.sheet_bluetooth_autoconnect.*
import kotlinx.android.synthetic.main.widget_subitem_sheet_multiple_choice.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.IntroActivity
import leko.valmx.uhrenprojekt.newP.adapters.MultipleChoicePopUpAdapter
import leko.valmx.uhrenprojekt.newP.bundles.misc.MultipleChoiceSheet

class ConnectBottomSheet : Sheet() {


    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(activity).inflate(R.layout.sheet_bluetooth_autoconnect, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(Blue.debug) {
            dismiss()
            return
        }

        feedBack.startFeedBack(100000)
        startSearch()


    }

    fun show(
        ctx: Context,
        width: Int? = null,
        func: ConnectBottomSheet.() -> Unit
    ): ConnectBottomSheet {
        this.windowContext = ctx
        this.width = width

        positiveListener = {
            dismiss()
            ConnectBottomSheet().show(ctx, width, func)
        }
        positiveText = "Suche Wiederholen"

        onNegative("Verbinde mit anderem Gerät") {

            startActivity(Intent(ctx, IntroActivity::class.java))


        }

//        displayNegativeButton(false)



        title("Autoverbindung")


        func(this)


        if (!Blue.debug)
            isCancelable = false
        this.show()
        return this
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun startSearch() {
        val ble = Blue.ble
        try {

            GlobalScope.launch {

                val address = "60:AB:D2:3A:E5:27"

                text_status.text = "Verbinde mit $address"

                Blue.connection = ble.scanFor(macAddress = address)

                val connection = Blue.connection

                if (connection == null) {
                    text_status.text = "Suche fehlgeschlagen"
                    return@launch
                }
                text_status.text = "Gerät Gefunden - Verbunden"


                for (s in connection.readableCharacteristics) {
                    Log.i(
                        "Chars",
                        connection.write("00000000-0000-0000-0000-000000000000", "Testing").toString()
                                + connection.isActive.toString()
                    )

                }


                dismiss()
            }
        } catch (e: Exception) {
        }


    }


}