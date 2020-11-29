package kennel.pheditor.screens.crop

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import events.MoveTick
import events.emit
import kotlin.math.floor

@SuppressLint("ClickableViewAccessibility")
class SideTick(
    val view: View,
    private val orientation: Int,
    private val id: Int) {
    private var dx = 0f
    private var dy = 0f

    init {
        view.setOnTouchListener { v, event ->
            Log.i("Catchers", "Wants to move $id")
            procMove(v, event)
        }
    }

    fun procMove(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            dx = event.rawX - view.x
            dy = event.rawY - view.y

        } else if (event.action == MotionEvent.ACTION_MOVE) {
            val nval: Float
            if (orientation == HORIZONTAL) {
                Log.i("Moving", "horizontal $id")
                nval = event.rawX - dx
            } else {
                Log.i("Moving", "vertical $id")
                nval = event.rawY - dy
            }
            emit(MoveTick(id, view, nval))
        }
        return true
    }

    companion object {
        val VERTICAL = 1
        val HORIZONTAL = 2
    }
}