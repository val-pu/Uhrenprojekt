package leko.valmx.uhrenprojekt.appearance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_appearance.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.appearance.colors.ColorPickerSheet

class AppearanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appearance)
        initViews()
    }

    fun initViews() {
        btn_color_settings.setOnClickListener {
            ColorPickerSheet().show(supportFragmentManager, "d")
        }
    }
}