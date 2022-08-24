package leko.valmx.uhrenprojekt.newP.autoconnect

import androidx.appcompat.app.AppCompatActivity
import leko.valmx.uhrenprojekt.bluetooth.Blue
import quevedo.soares.leandro.blemadeeasy.BLE

open class UhrAppActivity: AppCompatActivity() {

    override fun onStart() {
        initBLE()
        super.onStart()



    }

    override fun onPostResume() {
        super.onPostResume()
        ConnectBottomSheet().show(this) {


        }
    }

    fun initBLE() {
        Blue.ble = BLE(this).apply {

        }
    }


}