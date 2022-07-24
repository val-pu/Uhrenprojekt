package leko.valmx.uhrenprojekt.intro.bluetooth

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_bluetooth_device.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import quevedo.soares.leandro.blemadeeasy.models.BLEDevice
import java.util.*

class BluetoothDiscoveryAdapter(val inflater: LayoutInflater, val devices: LinkedList<BLEDevice>) :
    RecyclerView.Adapter<BluetoothDiscoveryAdapter.VH>() {


    class VH(item: View) : RecyclerView.ViewHolder(item) {
        @OptIn(DelicateCoroutinesApi::class)
        fun bind(device: BLEDevice) {
            itemView.name.text = device.device.name
            itemView.uid.text = device.device.address

            itemView.uid.setOnClickListener {
                Log.i("daw","Idea works")
                GlobalScope.launch {
                    val connection = Blue.ble.connect(device.device)

                    itemView.post {

                        Log.i("Connection","${connection!!.write(UUID.randomUUID().toString(),"10")}")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        inflater.inflate(R.layout.item_bluetooth_device, parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(devices[position])
    }

    override fun getItemCount(): Int = devices.size

}