package leko.valmx.uhrenprojekt.connection

import android.Manifest
import android.bluetooth.BluetoothClass
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.R
import quevedo.soares.leandro.blemadeeasy.BLE
import quevedo.soares.leandro.blemadeeasy.models.BLEDevice
import java.util.*

class BluetoothActivity : AppCompatActivity() {

    private final val TAG = "Uhr"
    private var devices = LinkedList<BLEDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBLE()
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {
        devices_recycler.layoutManager = LinearLayoutManager(this)
        devices_recycler.adapter = BluetoothDiscoveryAdapter(layoutInflater, devices)
    }

    override fun onStart() {
        super.onStart()
        if (!checkForPositionPermissions()) {
            requestLocationPermission()
        } else askPermissions()
    }

    lateinit var ble: BLE


    fun checkForPositionPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            askPermissions()
        }
    }

    fun scanForDevices() {
        ble.scanAsync(
            duration = 100000,
            onDiscover = { device ->
                Log.i(TAG, "Found ${device.name}")
                devices.add(device)
                devices_recycler.post {
                    devices_recycler.adapter?.notifyDataSetChanged()
                }

            },
            onFinish = { devices -> },
            onError = { errorCode -> }
        )
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun askPermissions() {


        GlobalScope.launch {
            val granted = ble.verifyPermissions { next ->
                next()
            }

            if (!granted) {
                makeToast("BLE Keine Berechtigungen")
                return@launch
            }

            Log.d(TAG, "BLE Berechtigungen erteilt")

            ble.verifyBluetoothAdapterState()
            ble.verifyLocationState()
            scanForDevices()
        }

    }

    fun initBLE() {
        ble = BLE(this).apply {
            verbose = true
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


}