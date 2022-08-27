package leko.valmx.uhrenprojekt.newP.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import leko.valmx.uhrenprojekt.R
import java.util.zip.Inflater

class LoadingDialog(val activity: Activity){

    private var dialog: AlertDialog? = null

    @SuppressLint("InflateParams")
    fun startActivity(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog!!.show()
    }

    fun dismiss(){
        dialog!!.dismiss()
    }
}
