package leko.valmx.uhrenprojekt.specials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_specials.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.specials.icons.IconAdapter

class SpecialsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specials)

        initViews()
    }

    private fun initViews() {
        val contentList: ArrayList<Int> = ArrayList()
            contentList.add(R.drawable.ic_sun)
            contentList.add(R.drawable.card)
            contentList.add(R.drawable.ic_start)


        val gimmickAdapter = IconAdapter(contentList, applicationContext)
        icon_recycler.adapter = gimmickAdapter
        icon_recycler.layoutManager = GridLayoutManager(applicationContext,3)

    }

}