package leko.valmx.uhrenprojekt.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class DataTBN(val context: Context) { // TBN = To be named

    private val PREF_ID = "Martins_Uhr"

    enum class ID(val id: String, val default: Any = "") {
        BACK_COLOR("backC", -1),
        FRONT_COLOR("frontC", -1),
        NIGHTMODE_ALPHA_PERCENT("nightmodeAlphaPerc", 10),
        TOGGLE_BACK("toggleB", false),
        NIGHTMODE_START_TIME("nm_on", 23 * 60 * 60L),
        NIGHTMODE_END_TIME("nm_off", 7 * 60 * 60L)


    }

    /**
     * SEHR DRY besserer Ansatz folgt TODO
     */

    val prefs = context.getSharedPreferences(PREF_ID, MODE_PRIVATE)

    var frontColor = loadSetting(ID.FRONT_COLOR)!!.toInt()
        set(value) {
            field = value
            saveSetting(ID.FRONT_COLOR, value)
        }

    var backColor = loadSetting(ID.BACK_COLOR)!!.toInt()
        set(value) {
            field = value
            saveSetting(ID.BACK_COLOR, value)
        }

    var nightModeAlpha = loadSetting(ID.NIGHTMODE_ALPHA_PERCENT)!!.toInt()
        set(value) {
            field = value
            saveSetting(ID.NIGHTMODE_ALPHA_PERCENT, value)
        }

    var nighModeStart = loadSetting(ID.NIGHTMODE_START_TIME)!!.toLong()
        set(value) {
            field = value
            saveSetting(ID.NIGHTMODE_START_TIME, value)
        }

    var nightModeEnd = loadSetting(ID.NIGHTMODE_END_TIME)!!.toLong()
        set(value) {
            field = value
            saveSetting(ID.NIGHTMODE_END_TIME, value)
        }

    fun getAndInvertBool(id: ID): Boolean {
        val ret = loadSetting(id).toBoolean()

        saveSetting(id, !ret)

        return ret
    }

    fun getBool(id: ID): Boolean = loadSetting(id).toBoolean()

    private fun loadSetting(id: ID) = prefs.getString(id.id, id.default.toString())

    private fun saveSetting(id: ID, toSave: Any) =
        prefs.edit().putString(id.id, toSave.toString()).apply()

}