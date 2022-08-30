package leko.valmx.uhrenprojekt.popup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.sheet_correction_layout.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue

class CorrectionBottomSheet(private val question: String,
                            private val answer: String,
                            private val command: String): Sheet()
{

    override fun onCreateLayoutView(): View {
        return LayoutInflater.from(context).inflate(R.layout.sheet_correction_layout, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sheet_answer_text.text = answer
    }

    fun show(ctx: Context,
             width: Int? = null,
             func: CorrectionBottomSheet.() -> Unit = {}): CorrectionBottomSheet {

        this.windowContext = ctx
        this.width = width

        positiveListener = {
            Blue.sendCommand(command)
            dismiss()
        }
        positiveText = "Senden"

        onNegative("Abbrechen") {
            dismiss()
        }
        title(question)
        func(this)
        this.show()
        return this
    }

    fun isValidInput(input: String): Boolean
    {
        return true
    }
}