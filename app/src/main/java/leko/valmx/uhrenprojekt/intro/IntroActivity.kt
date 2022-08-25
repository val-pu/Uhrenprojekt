package leko.valmx.uhrenprojekt.intro

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import leko.valmx.uhrenprojekt.MainActivity
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.bluetooth.BluetoothSearchSlide
import leko.valmx.uhrenprojekt.newP.CustomizerActivity
import quevedo.soares.leandro.blemadeeasy.BLE

class IntroActivity : AppIntro2() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val deviceName = Blue.getDeviceName(this)




        // Intro slides
/*
        addSlide(
            AppIntroFragment.createInstance(
                title = "Perfekt!",
                description = "Nun muss nur noch die Uhr per Bluetooth verbunden werden...."
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                title = "Willkommen!",
                titleColorRes = R.color.black,
                description = "Erstmal braucht die App ein paar Berechtigungen, um gut zu funktionieren."
            )
        )
*/
        Blue.ble = BLE(this)
        addSlide(BluetoothSearchSlide())

        // Nach dem ersten Slide wird gefragt, ob man Bluetooth & Standortinfos verwenden darf

        askForPermissions(
            permissions = arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            slideNumber = 1,
            required = true
        )
        isWizardMode = true
        if (deviceName != "") {

            startActivity(Intent(this, CustomizerActivity::class.java))

            return
        }
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)

        startActivity(Intent(this, MainActivity::class.java))
    }


}