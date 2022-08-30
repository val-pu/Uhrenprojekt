package leko.valmx.uhrenprojekt.parents

import androidx.appcompat.app.AppCompatActivity
import leko.valmx.uhrenprojekt.autoconnect.ConnectBottomSheet
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.etc.developertools.SendingSuccessInterface
import quevedo.soares.leandro.blemadeeasy.BLE

open class UhrAppActivity : AppCompatActivity(), Runnable, SendingSuccessInterface {

    init {
        isSheetDisplayed = false
    }

    override fun onStart() {
        Blue.initRelyInterface(this)
        initBLE()
        super.onStart()
        android.os.Handler().postDelayed(this, 20_000)
    }

    override fun onPostResume() {
        super.onPostResume()
        if (!Blue.debug) {
            ConnectBottomSheet.getInstance().show(this) { }
        }
    }

    fun initBLE() {
        Blue.ble = BLE(this).apply {
        }
    }

    override fun run() {
        if (Blue.connection != null && !Blue.connection!!.isActive) {
            ConnectBottomSheet.getInstance().show(this) {}
        }

        android.os.Handler().postDelayed(this, 20_000)

    }

    companion object {
        var isSheetDisplayed = false

    }

    override fun callReply(success: Int) {
        TODO("Not yet implemented")
    }
}