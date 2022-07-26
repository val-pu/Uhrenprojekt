package leko.valmx.uhrenprojekt.intro.bluetooth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_bluetooth_picker_sheet.*
import kotlinx.android.synthetic.main.fragment_bluetooth_picker_sheet.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.util.BeautifulBottomSheet
import quevedo.soares.leandro.blemadeeasy.models.BLEDevice
import java.util.*

class BlueToothPickSheet(val data: LinkedList<BLEDevice>): BeautifulBottomSheet() {
    override fun getLayout(): Int = R.layout.fragment_bluetooth_picker_sheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.adapter = BluetoothDiscoveryAdapter(layoutInflater,data)
        recycler.layoutManager = LinearLayoutManager(context)
    }


}