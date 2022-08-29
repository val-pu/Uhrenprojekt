package leko.valmx.uhrenprojekt.util

import leko.valmx.uhrenprojekt.popup.CorrectionBottomSheet

object Check {
    fun validInput(input: String, correctionTyoe: String): Boolean
    {
        if(correctionTyoe.equals("seton") || correctionTyoe.equals("setof")) {
            val r: Regex = Regex("([0-2][0-3]|[0-1][0-9]):[0-5][0-9]$")
            return r.matches(input)
        }
        if(correctionTyoe.equals("setal")) {
            val r: Regex = Regex("([0-2][0-3]|[0-1][0-9]):[0-5][0-9](:[0-5][0-9])?$")
            return r.matches(input)
        }
        if(correctionTyoe.equals("setad")){
            val r: Regex = Regex("(0?[0-9]|1[0-9]|20)$")
            return r.matches(input)
        }
        if(correctionTyoe.equals("setnb")){
            val maxRegx: String = "[0-2][0-5][0-5]"
            val lowRegx: String = "[0-1]?[0-9]?[0-9]"
            val r = Regex("($maxRegx|$lowRegx)$")
            return r.matches(input)
        }
        return false
    }
}