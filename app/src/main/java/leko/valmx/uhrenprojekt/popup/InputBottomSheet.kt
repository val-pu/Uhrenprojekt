package leko.valmx.uhrenprojekt.popup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.activity_nightmode.*
import kotlinx.android.synthetic.main.sheet_input_layout.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.intro.IntroActivity
import leko.valmx.uhrenprojekt.newP.autoconnect.ConnectBottomSheet
import leko.valmx.uhrenprojekt.newP.autoconnect.UhrAppActivity
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper
import leko.valmx.uhrenprojekt.util.Check
import org.w3c.dom.Text

class InputBottomSheet(private val header: String,
                       private val description: String,
                       private val command: String,
                       private val invalidInputInterface: InvalidInputInterface): Sheet()
{

    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(context).inflate(R.layout.sheet_input_layout, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_sheet_decription.text = description
    }

    fun show(ctx: Context,
             width: Int? = null,
             func: InputBottomSheet.() -> Unit = {}): InputBottomSheet {

        this.windowContext = ctx
        this.width = width

        positiveListener = {
            val input: String = input_line.text.toString()
            var question = ""
            var answer = ""
            if(command.equals("seton")) {
                question = "Beginn des Nachtmodus auf diese Uhrzeit setzen?"
                answer = "$input Uhr"
            }
            if(command.equals("setof")) {
                question = "Ende des Nachtmodus auf diese Uhrzeit setzen?"
                answer = "$input Uhr"
            }
            if(command.equals("setnb")) {
                question = "Helligkeit des Nachtmodus auf diesen Wert setzen?"
                answer = input
            }
            dismiss()
            if(Check.validInput(input, command)) {
                CorrectionBottomSheet(question, answer, "$command $input").show(ctx)
            }
            else{
                invalidInputInterface.onInvalidInput()
            }
        }
        positiveText = "Fertig"

        onNegative("Abbrechen") {
            dismiss()
        }
        title(header)
        func(this)
        this.show()
        return this
    }
}