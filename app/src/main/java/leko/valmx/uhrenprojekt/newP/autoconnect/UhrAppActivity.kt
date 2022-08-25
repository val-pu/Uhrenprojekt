package leko.valmx.uhrenprojekt.newP.autoconnect

import androidx.appcompat.app.AppCompatActivity
import leko.valmx.uhrenprojekt.bluetooth.Blue
import quevedo.soares.leandro.blemadeeasy.BLE
import java.util.logging.Handler

open class UhrAppActivity : AppCompatActivity(), Runnable {

    init {
        isSheetDisplayed = false
    }

    override fun onStart() {
        initBLE()
        super.onStart()
        android.os.Handler().postDelayed(this, 20000)


    }

    override fun onPostResume() {
        super.onPostResume()
        if (!Blue.debug)
            ConnectBottomSheet().show(this) {


            }
    }

    fun initBLE() {
        Blue.ble = BLE(this).apply {

        }
    }

    override fun run() {
        if (Blue.connection != null && !Blue.connection!!.isActive) {
            ConnectBottomSheet().show(this) {}
        }

        android.os.Handler().postDelayed(this, 20000)
    }

    companion object{
        var isSheetDisplayed = false

    }
}