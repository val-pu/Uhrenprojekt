package leko.valmx.uhrenprojekt.popup

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.loading_dialog.view.*
import leko.valmx.uhrenprojekt.R
import java.util.zip.Inflater

class LoadingDialog(private val activity: Activity) {

    private var dialog: AlertDialog? = null

    @SuppressLint("InflateParams")
    fun start() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog!!.show()
    }

    fun dismiss() {
        dialog!!.dismiss()
    }
    fun start(duration: Long){
        start()
        val handler: Handler = Handler()
        handler.postDelayed(Runnable {
            dismiss()
        }, duration)
    }
}
