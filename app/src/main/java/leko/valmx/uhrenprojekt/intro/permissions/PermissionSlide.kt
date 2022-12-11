package leko.valmx.uhrenprojekt.intro.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.SlidePolicy
import com.google.android.material.snackbar.Snackbar
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.slide_permission.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.intro.IntroActivity

class PermissionSlide() : SlidePolicy, Fragment() {
    override var isPolicyRespected: Boolean = true

    lateinit var intro: IntroActivity

    constructor(intro: IntroActivity) : this() {
        this.intro = intro
    }


    override fun onUserIllegallyRequestedNextPage() {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.slide_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_permissions.setOnClickListener {

            // https://developer.android.com/training/permissions/requesting

            val perms =
                arrayOf<String>(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_PRIVILEGED,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_FINE_LOCATION

                )
            PermissionX.init(this).permissions(*perms)
                .request { allGranted, grantedList, deniedList ->


                    if (allGranted || grantedList.size != 0) {
                        intro.nextSlide()
                    } else {
                        Snackbar.make(requireView(),R.string.perms_fail,Snackbar.LENGTH_SHORT).show()
                    }

                }


        }
    }


}