package leko.valmx.uhrenprojekt.customizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R

class CustomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizer)
        initViews()
    }

    private fun initViews(){
        gimmick_recycler.layoutManager = LinearLayoutManager(applicationContext)
        gimmick_recycler.adapter = GimmickAdapter(layoutInflater)
    }
}