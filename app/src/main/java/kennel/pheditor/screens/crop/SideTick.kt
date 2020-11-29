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
    view: View,
    private val orientation: Int,
    private val id: Int) {
    private var dx = 0f
    private var dy = 0f

    init {
        view.setOnTouchListener { v, event ->
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
                nval = event.rawX - dx
                view.x = nval
            } else {
                nval = event.rawY - dy
                view.y = nval
            }
            //emit(MoveTick(id, view, a, b))
        }
        return true
    }

    companion object {
        val VERTICAL = 1
        val HORIZONTAL = 2
    }
}