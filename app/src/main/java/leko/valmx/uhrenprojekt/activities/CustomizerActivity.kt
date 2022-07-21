package leko.valmx.uhrenprojekt.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.customizer.gimmickExec.GimmickAdapter

class CustomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_customizer)
        initViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {


        btn_erscheinung.setOnClickListener {
            startActivity(Intent(this, AppearanceActivity::class.java))
        }

        btn_gimmicks.setOnClickListener {
            startActivity(Intent(this, GimmickActivity::class.java))
        }
    }
}