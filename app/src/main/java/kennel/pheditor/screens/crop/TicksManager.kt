package kennel.pheditor.screens.crop

import android.content.Context
import android.view.View
import events.MoveTick
import events.register

class TicksManager(
    private val app: Context,
    private val startX: Int,
    private val startY: Int,
    private val startWidth: Int,
    private val startHeight: Int,
    private val dim1: Int,
    private val dim2: Int
) {
    private val ticks = mutableListOf<SideTick>()
    init {
        register<MoveTick> {

        }
        generateTicks()
    }

    private fun generateTicks() {
        val t1 = View(app)
    }
}