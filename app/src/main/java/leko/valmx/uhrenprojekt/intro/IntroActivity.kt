package leko.valmx.uhrenprojekt.intro

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.bluetooth.BluetoothSearchSlide
import quevedo.soares.leandro.blemadeeasy.BLE

class IntroActivity : AppIntro2() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBLE() // Initiiere Bluetooth

        // Intro slides

        addSlide(
            AppIntroFragment.createInstance(
                title = "Willkommen!",
                titleColorRes = R.color.black,
                description = "Erstmal braucht die App ein paar Berechtigungen, um gut zu funktionieren."
            )
        )

        addSlide(
            AppIntroFragment.createInstance(
                title = "Perfekt!",
                description = "Nun muss nur noch die Uhr per Bluetooth verbunden werden...."
            )
        )

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
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }

    fun initBLE() {
        Blue.ble = BLE(this).apply {
            verbose = true
        }
    }
}