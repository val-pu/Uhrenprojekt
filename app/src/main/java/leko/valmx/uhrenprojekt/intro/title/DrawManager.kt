package leko.valmx.uhrenprojekt.intro.title

import leko.valmx.uhrenprojekt.util.Util
import kotlin.random.Random

class DrawManager(val v: UhrTitelView) {

    lateinit var textStarts: Array<Int>

    fun init() {
        val gridManager = v.gridManager

        val rawText = v.textArr

        textStarts = Array(rawText.size) { _ -> 0 }

        val maxChars = gridManager!!.charsPerLine

        val preparedText = arrayOfNulls<String?>(rawText.size)

        for ((i, textLine) in rawText.withIndex()) {

            val lineSize = textLine.length

            val maxStartSize = maxChars - lineSize

            val textStart = maxStartSize - Random.nextInt(maxStartSize)

            textStarts[i] = textStart

            var result = ""

            repeat(maxChars) { j ->
                result +=
                    if (j < textStart || j >= textStart + lineSize) (30 + Random.nextInt(92)).toChar()
                        .toChar()
                    else textLine[j - textStart]
            }

            preparedText[i] = result

        }

        val paintManager = v.paintManager

        val inactive = paintManager.inactivePaint
        val active = paintManager.activePaint

        val back = paintManager.backPaint


        val c = v.c

        c.drawRoundRect(
            0F, 0F, c.width.toFloat(),
            c.height.toFloat(), gridManager.radius, gridManager.radius, back
        )

        for ((i, textLine) in preparedText.withIndex()) {

            val textStart = textStarts[i]

            val lineSize = rawText[i].length

            repeat(textLine!!.length) { j ->

                val paint = if (j < textStart || j >= textStart + lineSize) inactive
                else active

                Util.drawText(
                    textLine!![j].toString(),
                    gridManager!!.getLetterRect(j, i),
                    v.c,
                    paint,
                    v.context
                )
            }

        }
        v.invalidate()


    }

}