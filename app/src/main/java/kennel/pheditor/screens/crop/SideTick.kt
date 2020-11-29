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
    private var dl = 0
    private var dt = 0
    private var dr = 0
    private var db = 0

    init {
        view.setOnTouchListener { v, event ->
            procMove(v, event)
        }
    }

    fun procMove(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            dl = floor(event.rawX - view.left).toInt()
            dr = floor(event.rawX - view.right).toInt()
            dt = floor(event.rawY - view.top).toInt()
            db = floor(event.rawY - view.bottom).toInt()

        } else if (event.action == MotionEvent.ACTION_MOVE) {
            Log.i("Nnab", "Event: ${event.rawX}, ${event.rawY}")
            val a: Int
            val b: Int
            if (orientation == HORIZONTAL) {
                a = floor(event.rawX - dl).toInt()
                b = floor(event.rawX - dr).toInt()
            } else {
                a = floor(event.rawY - dt).toInt()
                b = floor(event.rawY - db).toInt()
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