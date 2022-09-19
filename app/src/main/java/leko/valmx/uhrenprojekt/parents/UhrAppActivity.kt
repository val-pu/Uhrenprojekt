package leko.valmx.uhrenprojekt.parents

import androidx.appcompat.app.AppCompatActivity
import leko.valmx.uhrenprojekt.autoconnect.ConnectBottomSheet
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.etc.developertools.SendingSuccessInterface
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.BluetoothConnection

open class UhrAppActivity : AppCompatActivity(), Runnable {

    companion object {
        var connection: BluetoothConnection? = null
        var isSheetDisplayed = false

        fun send(cmd: String) {
            if (connection != null)
                connection?.write("0000FFE1-0000-1000-8000-00805F9B34FB", cmd)!!
        }

    }

    lateinit var ble: BLE

    init {
        isSheetDisplayed = false
    }

    override fun onStart() {
        initBLE()
        super.onStart()
        android.os.Handler().postDelayed(this, 20_000)
    }

    override fun onPostResume() {
        super.onPostResume()

    }

    fun initBLE() {
        ble = BLE(this).apply {

            if (connection == null || !connection!!.isActive)

                ConnectBottomSheet.getInstance(this@UhrAppActivity).show(this@UhrAppActivity) { }

        }
    }

    override fun run() {
        if (Blue.connection != null && !Blue.connection!!.isActive) {
            ConnectBottomSheet.getInstance(this).show(this) {}
        }

        android.os.Handler().postDelayed(this, 20_000)

    }
}