package leko.valmx.uhrenprojekt.widgets

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import com.maxkeppeler.sheets.color.ColorSheet
import kotlinx.android.synthetic.main.widget_color_colorchange.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.adapters.widgets.ColorAdapter
import leko.valmx.uhrenprojekt.data.DataTBN
import leko.valmx.uhrenprojekt.widgets.bundles.ChoiceItem

class ColorWidget : Widget() {

    @SuppressLint("NewApi")
    override fun init() {
        title("Farberscheinung")
        description("Ändere das Thema der Uhr")

        var update: () -> Unit = { }


        custom(R.layout.widget_color_colorchange) {
            @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
            update = {


                val fc =
                    if (data.frontColor == -1) resources.getColor(R.color.default_front_clock) else data.frontColor
                val bc =
                    if (data.backColor == -1) resources.getColor(R.color.default_back_clock) else data.backColor

                foreground_color.background = resources.getDrawable(R.drawable.card)
                foreground_color.background.setTint(fc)
                foreground_color_info.text = "#${fc.toString(16)}"

                background_color.background = resources.getDrawable(R.drawable.card)
                background_color.background.setTint(bc)
                background_color_info.text = "#${bc.toString(16)}"
                invalidate()
            }

            update()

            back_color_change_btn.setOnClickListener {
                ColorSheet().show(context!!) {
                    title("Hintergrundfarbe")
                    onPositive {
                        val color = Color.valueOf(it)
                        executeCommand("setbg ${(color.red() * 255).toInt()},${(color.green() * 255).toInt()},${(color.blue() * 255).toInt()}")
                        data.backColor = it
                        update()
                    }
                }
            }

            text_color_change_btn.setOnClickListener {
                ColorSheet().show(context) {
                    title("Vordergrundfarbe")
                    disableAlpha()
                    onPositive {
                        val color = Color.valueOf(it)
                        executeCommand("setdp ${(color.red() * 255).toInt()},${(color.green() * 255).toInt()},${(color.blue() * 255).toInt()}")
                        data.frontColor = it
                        update()
                    }
                }
            }

        }

        toggle("Hintergrundbeleuchtung", "Schalte den Hintergrund an oder aus", {
            data.getBool(DataTBN.ID.TOGGLE_BACK)
        }, {
            data.getAndInvertBool(DataTBN.ID.TOGGLE_BACK)
            executeCommand("bg")
        })


/*

        redirect("Vordergrundfarbe", "Setze eine eigene Vordergrundfarbe") {



        }*/
/*

        multipleChoice(command = "mc", name = "Textfarbmodus") {
            it.add(ChoiceItem("Einfarbig", "Jedes Wort in der selben Farbe", "0"))
            it.add(ChoiceItem("Leicht Variiert", "Jedes Wort in einer leicht anderen Farbe", "1"))
            it.add(ChoiceItem("Zufällig", "Jedes Wort in einer zufälligen Farbe", "2"))
        }
        multipleChoice(command = "sm", name = "Texterscheinungsmodus") {
            it.add(ChoiceItem("Unmittelbar", "Neue Worte erscheinen unmittelbar", "0"))
            it.add(
                ChoiceItem(
                    "Von links nach rechts",
                    "Neue Worte laufen von links nach rechts herein",
                    "1"
                )
            )
            it.add(
                ChoiceItem(
                    "Alle von rechts nach links",
                    "Alle Worte laufen nach links hinaus und von rechts hinein",
                    "2"
                )
            )
            it.add(ChoiceItem("Gedimmt", "Neue Worte erscheinen sanft gedimmt", "3"))
            it.add(ChoiceItem("Flackernd", "Neue Worte erscheinen flackernd", "4"))
        }
*/

//        recycler(
//            "Voreinstellungen",
//            ColorAdapter(update),
//            "Voreingestellte Farbthema",
//            GridLayoutManager(view.context, 5)
//        )


        command("Alle Farben", "showcolors", "Zeige alle verfügbaren Farben an")
        command("Zufallsfarbe", "cc", "Wechsel die Farbe des Displays auf eine Zufallsfarbe")
        command("Smoothmode", "sm", "Schalte die verschiedenen Smoothmodi durch")
    }


}