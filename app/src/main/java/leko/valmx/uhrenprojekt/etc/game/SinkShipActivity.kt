package leko.valmx.uhrenprojekt.etc.game

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sink_ship.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.parents.UhrAppActivity

class SinkShipActivity : UhrAppActivity() {

//    val loadingDialog: LoadingDialog = LoadingDialog(this)
    companion object {
        var tries: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sink_ship)

        Matrix.init()

        //init buttons
        sv_btn_up.setOnClickListener{Matrix.moveUp()}
        sv_btn_down.setOnClickListener{Matrix.moveDown()}
        sv_btn_left.setOnClickListener{Matrix.moveLeft()}
        sv_btn_right.setOnClickListener{Matrix.moveRight()}
        sv_btn_choose.setOnClickListener{Matrix.pick()}
    }

    private object Matrix
    {
        private val position: Array<Int> = arrayOf(0, 0)

        // stelle 0 -> ship?
        // stelle  1 -> picked?
        private var tiles = Array(11, { Array(11, { arrayOf(false, false) }) })

        fun init(){
            tiles = Array(11, { Array(11, { arrayOf(false, false) }) })
        }

        fun moveUp(){
            if(position[1] != 10) position[1] += 1
            if(position[1] == 10){

            }
            sendUpdate()
        }

        fun moveDown(){
            if(position[1] != 0) position[1] -= 1
            if(position[1] == 0){

            }
            sendUpdate()
        }

        fun moveRight(){
            if(position[0] != 10) position[0] += 1
            if(position[0] == 10){

            }
            sendUpdate()
        }

        fun moveLeft(){
            if(position[0] != 0) position[0] -= 1
            if(position[0] == 0){

            }
            sendUpdate()
        }

        fun pick(){
            val x = position[0]
            val y = position[1]
            if(tiles[x][y][0] == false)
            {
                tries += 1
                tiles[x][y][1] = true
            }
            sendUpdate()
        }

        fun sendUpdate(){
            // Blue.sendCommand("UPDATE_MATRIX:{}")
        }
    }

}