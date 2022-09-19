package leko.valmx.uhrenprojekt.intro.bluetooth

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.appintro.SlidePolicy
import kotlinx.android.synthetic.main.fragment_bluetooth.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.utils.WidgetHelper
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.models.BLEDevice
import java.util.*

@DelicateCoroutinesApi
class BluetoothSearchSlide(override var isPolicyRespected: Boolean = true) : Fragment(),
    SlidePolicy {

    lateinit var ble: BLE

    override fun onUserIllegallyRequestedNextPage() {}

    var container: ViewGroup? = null

    private var devices = LinkedList<BLEDevice>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        return inflater.inflate(R.layout.fragment_bluetooth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        scanForDevices() // Erster Scan wird initiiert

        pick_card.setOnClickListener {
            BlueToothPickSheet(devices).show(parentFragmentManager, "")
        }

        ble = BLE(this)

        initViews()

    }

    fun initViews() {
        connect.setOnClickListener {
            GlobalScope.launch {
                Log.i("Connecting","Started Connecting to $selectedDevice")
                val connection =  ble.scanFor(
                    macAddress = selectedDevice!!.macAddress,
                )



                if (connection != null) {
                    Log.i("Konnte Schreiben",connection.write("0000FFE1-0000-1000-8000-00805F9B34FB","matrix").toString())
                    connect.post {
                        card_found_device.visibility = GONE
                        pick_card.visibility = GONE
                        card_success.visibility = VISIBLE
                    }

                    val sharedPreferences =
                        requireActivity().getSharedPreferences(WidgetHelper.PREF_ID,MODE_PRIVATE)

                    sharedPreferences.edit().putString(Blue.NAME_ID,selectedDevice!!.macAddress).apply()


                }

            }

        }

        btn_search.setOnClickListener {
            scanForDevices()
            feedbackView.startFeedBack(searchDuration)
        }
    }


    /**
    Sucht nach neuen Geräten & published diese im Recyclerview
     */

    private val possibleClockNames =
        arrayListOf("HMSoft", "MLT-BT05", "BT05")

    private val searchDuration = 30000

    @SuppressLint("MissingPermission")
    private fun scanForDevices() {
        devices.clear()
        ble.scanAsync(
            duration = searchDuration.toLong(),
            onDiscover = { device ->
                Log.i(tag, "Found ${device.name} + ${device.device.bluetoothClass}")

//                if (device.name == "") return@scanAsync

                if (possibleClockNames.contains(device.name)) selectDevice(device)
                devices.add(device)

            },
            onFinish = { _ -> },
            onError = { _ -> }
        )
    }

    var selectedDevice: BLEDevice? = null

    fun selectDevice(device: BLEDevice) {
        selectedDevice = device
        device_name.text = "${device.name} gefunden"

        if (card_found_device.visibility == GONE) {
            card_found_device.alpha = 0F
            card_found_device.visibility = VISIBLE
            card_found_device.animate().apply {
                alpha(1F)
            }
        }

    }


}