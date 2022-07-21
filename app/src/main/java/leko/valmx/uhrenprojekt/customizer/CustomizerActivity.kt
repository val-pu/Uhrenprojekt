package leko.valmx.uhrenprojekt.customizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.customizer.sheets.ColorPickerAdapter

class CustomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizer)
        initViews()
    }

    private fun initViews() {
//        gimmick_recycler.layoutManager = GridLayoutManager(applicationContext,3)
//        gimmick_recycler.adapter = ColorPickerAdapter(layoutInflater)

        btn_erscheinung.setOnClickListener {
            startActivity(Intent(this,AppearanceActivity::class.java))
        }
    }
}