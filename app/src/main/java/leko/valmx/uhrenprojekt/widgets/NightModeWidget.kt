package leko.valmx.uhrenprojekt.widgets

import com.maxkeppeler.sheets.time.TimeFormat
import com.maxkeppeler.sheets.time.TimeSheet
import kotlinx.android.synthetic.main.widget_nightmode.view.*
import leko.valmx.uhrenprojekt.R

class NightModeWidget : Widget() {
    override fun init() {
        title("Nachtmodus")
        description("Ein praktischer Modus, um im gleichen Raum schlafen zu können")

        custom(R.layout.widget_nightmode) {


            fun update() {
                nightmode_end.text = beautifyTime(data.nightModeEnd)
                nightmode_start.text = beautifyTime(data.nighModeStart)

                val currentDayMins =
                    System.currentTimeMillis() % (1000L * 60 * 60 * 24) / (1000 * 60)

                val start = data.nighModeStart
                val end = data.nightModeEnd


            }

            update()

            body.setOnClickListener {
                showSheet(
                    R.string.nightmode_title_on,
                    R.string.nightmode_description_on,
                    data.nighModeStart
                ) { newStartTime ->
                    run {

                        showSheet(
                            R.string.nightmode_title_off,
                            R.string.nightmode_description_off,
                            data.nightModeEnd
                        ) { newEndTime ->
                            data.nightModeEnd = newEndTime
                            data.nighModeStart = newStartTime

                            update()
                        }
                    }
                }

            }

        }
    }

    private fun beautifyTime(time: Long): String {
        "${time / (60 * 60)}:${time % (60*60)/60}".apply {
            split(":").apply {
                if (this[1].length == 1) {
                    return this[0] + ":0" + this[1].last()
                }
            }

            return this
        }
    }


    fun showSheet(title: Int, description: Int, time: Long, onPos: (Long) -> Unit) {
        TimeSheet().show(context) {

            title(title)
//            description(resources.getString(description))
            format(TimeFormat.HH_MM)
            maxTime(60 * 60 * 24)
            currentTime(time)
            onPositive("Weiter") { onPos(it) }
            onNegative("Abbrechen")
        }
    }


}
