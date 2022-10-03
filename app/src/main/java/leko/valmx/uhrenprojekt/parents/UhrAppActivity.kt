package leko.valmx.uhrenprojekt.parents

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import leko.valmx.uhrenprojekt.autoconnect.ConnectBottomSheet
import leko.valmx.uhrenprojekt.bluetooth.Blue
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.BluetoothConnection
import java.util.concurrent.LinkedBlockingQueue

@Suppress("DEPRECATION")
open class UhrAppActivity : AppCompatActivity(), Runnable {

    companion object : Runnable {
        var connection: BluetoothConnection? = null
        var isSheetDisplayed = false

        var executionThreadRunning = false
        var currentlyExecuted: Command? = null

        var onCmd: OnCommandListener? = null
        var scheduledCommands = LinkedBlockingQueue<Command>()


        fun send(cmd: Command) {

            scheduledCommands.add(cmd)


            if (executionThreadRunning) return

            executionThreadRunning = true
            onCmd?.onExecutionStart()
            run()
        }

        override fun run() {

            onCmd?.onCommandSizeUpdated(scheduledCommands.size)

            currentlyExecuted = scheduledCommands.poll()

            if (currentlyExecuted == null) {
                executionThreadRunning = false
                onCmd?.onExecutionFinish()
                return
            }

            connection?.write(
                "0000FFE1-0000-1000-8000-00805F9B34FB",
                currentlyExecuted?.cmd ?: "euro"
            )

            Handler().postDelayed(this, 10000)
        }

    }

    class Command(var cmd: String, var desc: String = "No Description.")

    interface OnCommandListener {

        fun onCommandSizeUpdated(newSize: Int)

        fun onExecutionFinish()

        fun onExecutionStart()

        fun onDisconnect()

    }

    lateinit var ble: BLE

    init {
        isSheetDisplayed = false
    }

    override fun onStart() {
        initBLE()
        super.onStart()
        android.os.Handler().postDelayed(this, 20_000)

        if(this is OnCommandListener) onCmd = this

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

    override fun onPause() {
        super.onPause()
        onCmd = null
    }

}