package leko.valmx.uhrenprojekt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_gimmick.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.customizer.gimmickExec.GimmickAdapter

class GimmickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gimmick)

        initStuff()
    }

    fun initStuff() {
        val contentList: ArrayList<Int> = ArrayList()
            contentList.add(R.drawable.ic_sun)
            contentList.add(R.drawable.card)
            contentList.add(R.drawable.ic_start)


        val gimmickAdapter = GimmickAdapter(contentList, applicationContext)
        gimmick_recycler.adapter = gimmickAdapter
        gimmick_recycler.layoutManager = LinearLayoutManager(applicationContext)

    }

}