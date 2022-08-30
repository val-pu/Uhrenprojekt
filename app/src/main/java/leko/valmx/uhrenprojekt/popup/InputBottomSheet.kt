package leko.valmx.uhrenprojekt.popup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.sheet_input_layout.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.util.Check

class InputBottomSheet(private val header: String,
                       private val description: String,
                       private val hint: String? = null,
                       private val command: String,
                       private val invalidInputInterface: InvalidInputInterface): Sheet()
{

    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(context).inflate(R.layout.sheet_input_layout, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_sheet_decription.text = description
        if(hint != null) textInputLayout123.hint = hint
    }

    fun show(ctx: Context,
             width: Int? = null,
             func: InputBottomSheet.() -> Unit = {}): InputBottomSheet {

        this.windowContext = ctx
        this.width = width

        positiveListener = {
            var input: String = input_line.text.toString()
            dismiss()
            if(Check.validInput(input, command)) {
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
                if(command.equals("setal")){
                    question = "Alarm auf diese Uhrzeit setzen?"
                    val sp = input.split(":")
                    val hh = sp.get(0)
                    val mm = sp.get(1)
                    var ss: String? = null
                    if(sp.size == 3) ss = sp.get(2)
                    var res = ""
                    if(ss == null){
                        res = "$hh:$mm Uhr"
                    }
                    else{
                        if(!ss.equals("00")) res = "$hh:$mm Uhr und $ss Sekunden"
                        else res = "$hh:$mm Uhr"
                    }
                    answer = res
                }
                if(command.equals("setad")){
                    question = "Alarmdauer auf diesen Wert setzen?"
                    answer = input
                    if(input.length == 1) input = "0$input"
                }
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