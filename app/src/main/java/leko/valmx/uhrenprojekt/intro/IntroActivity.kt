package leko.valmx.uhrenprojekt.intro

import android.Manifest
import android.Manifest.permission.BLUETOOTH
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.DelicateCoroutinesApi
import leko.valmx.uhrenprojekt.SimpleControlActivity
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.bluetooth.BluetoothSearchSlide
import leko.valmx.uhrenprojekt.intro.permissions.PermissionSlide

class IntroActivity : AppIntro2() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val deviceName = Blue.getDeviceName(this)

        addSlide(PermissionSlide(this))
        addSlide(BluetoothSearchSlide())

        isImmersive = true
        showStatusBar(false)
        isIndicatorEnabled = false


//        if (deviceName != "") {
//            startActivity(Intent(this, SimpleControlActivity::class.java))
//            return
//        }
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        startActivity(Intent(this, SimpleControlActivity::class.java))
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this, SimpleControlActivity::class.java))
    }

    val PERMISSION_RQ_CODE = 101111

    @RequiresApi(Build.VERSION_CODES.S)
    fun requestPermissions() {
        onNextSlide()
        val perms =
            arrayOf<String>(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_SCAN,Manifest.permission.BLUETOOTH_PRIVILEGED, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_FINE_LOCATION)
        PermissionX.init(this).permissions(*perms).request { allGranted, grantedList, deniedList ->

        }

    }
}