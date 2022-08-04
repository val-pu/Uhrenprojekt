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
        val contentList: ArrayList<IconInfo> = ArrayList()
            contentList.add(IconInfo("Herz",R.drawable.ic_heart,"heart"))
            contentList.add(IconInfo("Smiley",R.drawable.ic_smile,"smiley"))
            contentList.add(IconInfo("Haken",R.drawable.ic_check,"check"))
//            contentList.add(IconInfo("Kreuz",R.drawable.ic_smile,"cross"))
        contentList.add(IconInfo("Plus",R.drawable.ic_plus,"plus"))
        contentList.add(IconInfo("Mond",R.drawable.ic_moon,"moon"))
        contentList.add(IconInfo("Doppelpfeil",R.drawable.ic_chevrons_right,"arrow"))


        val gimmickAdapter = IconAdapter(contentList, applicationContext)
        icon_recycler.adapter = gimmickAdapter
        icon_recycler.layoutManager = GridLayoutManager(applicationContext,3)

    }

    class IconInfo(val name: String, val id: Int, val cmd: String)

}