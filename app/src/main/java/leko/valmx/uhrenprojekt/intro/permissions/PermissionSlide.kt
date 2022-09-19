package leko.valmx.uhrenprojekt.intro.permissions

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
import kotlinx.android.synthetic.main.slide_permission.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.intro.IntroActivity

class PermissionSlide(val intro: IntroActivity) : SlidePolicy, Fragment() {
    override var isPolicyRespected: Boolean = true

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

            intro.requestPermissions()


        }
    }


}