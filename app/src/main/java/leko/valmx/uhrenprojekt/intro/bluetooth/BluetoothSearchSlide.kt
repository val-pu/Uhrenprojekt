package leko.valmx.uhrenprojekt.intro.bluetooth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.appintro.SlidePolicy
import kotlinx.android.synthetic.main.fragment_bleutooth.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import quevedo.soares.leandro.blemadeeasy.models.BLEDevice
import java.util.*

class BluetoothSearchSlide(override var isPolicyRespected: Boolean = false) : Fragment(),
    SlidePolicy {

    override fun onUserIllegallyRequestedNextPage() {}

    private val TAG = "Uhr"
    private var devices = LinkedList<BLEDevice>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bluetooth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initiiere Recyclerview
        devices_recycler.adapter = BluetoothDiscoveryAdapter(layoutInflater,devices)
        devices_recycler.layoutManager = LinearLayoutManager(context)

        scanForDevices() // Erster Scan wird initiiert
    }


    /**
        Sucht nach neuen Geräten & published diese im Recyclerview
     */

    fun scanForDevices() {
        devices.clear()
        Blue.ble.scanAsync(
            duration = 100000,
            onDiscover = { device ->
                Log.i(TAG, "Found ${device.name} + ${device.device.bluetoothClass}")

                // Wurde das Device schon gefunden wird returnt
                if (devices.contains(device)) return@scanAsync

                devices.add(device)
                if(devices_recycler != null)
                devices_recycler.post {
                    devices_recycler.adapter?.notifyDataSetChanged()
                }


            },
            onFinish = { devices -> },
            onError = { errorCode -> }
        )
    }

}