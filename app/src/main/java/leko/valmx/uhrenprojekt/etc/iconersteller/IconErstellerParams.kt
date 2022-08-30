package leko.valmx.uhrenprojekt.etc.iconersteller

import android.content.Context
import android.graphics.Canvas

class IconErstellerParams(val ctx: Context, val canvas: Canvas, val v: IconCreatorView) {

    var paintManager: PaintManager = PaintManager(this)
    var drawManager: DrawManager = DrawManager(this)
    var dimensionManager: DimensionManager = DimensionManager(this)
    var interactionManager: InteractionManager = InteractionManager(this)
}