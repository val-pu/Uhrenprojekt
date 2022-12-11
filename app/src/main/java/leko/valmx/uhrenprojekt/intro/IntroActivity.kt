package leko.valmx.uhrenprojekt.intro

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.appintro.AppIntro
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroPageTransformerType
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.coroutines.DelicateCoroutinesApi
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.SimpleControlActivity
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.bluetooth.BluetoothSearchSlide
import leko.valmx.uhrenprojekt.intro.permissions.PermissionSlide

class IntroActivity : AppCompatActivity() {

    val slides: Array<Fragment> = arrayOf(PermissionSlide(this), BluetoothSearchSlide(this))
    var currentSlide = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



        val deviceName = Blue.getDeviceName(this)

        setContentView(R.layout.activity_intro)


//        if (deviceName != "") {
//            startActivity(Intent(this, SimpleControlActivity::class.java))
//            return
//        }

        nextSlide()
    }


    val PERMISSION_RQ_CODE = 101111

    fun nextSlide() {

        if (currentSlide == slides.size) {

            startActivity(Intent(this, SimpleControlActivity::class.java))

            return
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, slides[currentSlide]).commit()

        currentSlide++

    }

}