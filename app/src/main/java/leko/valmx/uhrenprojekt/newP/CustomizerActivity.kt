package leko.valmx.uhrenprojekt.newP

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.adapters.SavedWidgetsAdapter
import leko.valmx.uhrenprojekt.newP.adapters.WidgetContentAdapter

class CustomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizer)
        recycler.adapter = SavedWidgetsAdapter(supportFragmentManager)
    }
}