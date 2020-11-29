package kennel.pheditor.screens.crop

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.Log
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import events.MoveTick
import events.emit
import events.register
import kennel.pheditor.databinding.CropFragmentBinding


class TicksManager(
    private val app: Context,
    private val binding: CropFragmentBinding,
    private val stX: Float,
    private val stY: Float,
    private val stWid: Float,
    private val stHei: Float,
    private val dim1: Int,
    private val dim2: Int,
    private val color: Int,
) {
    private val ticks = mutableListOf<SideTick>()
    private val dDim1 = 200
    private val dDim2 = 100
    private val radius = 10f
    private val min = 10

    init {
        register<MoveTick> {
            Log.i("Events", "got ev")
            when (it.id) {
                0 -> {
                    if (it.dim < ticks[1].view.y - min && it.dim >= stY - dim2/2) {
                        it.view.y = it.dim
                    }
                }
                1 -> {
                    if (it.dim > ticks[0].view.y + min && it.dim <= stY + stHei - dim2/2) {
                        it.view.y = it.dim
                    }
                }
                2 -> {
                    if (it.dim < ticks[3].view.x - min && it.dim >= stX - dim2/2) {
                        it.view.x = it.dim
                    }
                }
                3 -> {
                    if (it.dim > ticks[2].view.x + min && it.dim <= stX + stWid - dim2/2) {
                        it.view.x = it.dim
                    }
                }
            }
        }
        generateTicks()
    }

    private fun generateTicks() {

        val outerRadii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        val innerRadii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        val borderDrawable = ShapeDrawable(
            RoundRectShape(
                outerRadii,
                null,  // Inset
                innerRadii
            )
        )
        borderDrawable.paint.color = color
        borderDrawable.paint.style = Paint.Style.FILL

        val backgroundShape = ShapeDrawable(
            RoundRectShape(
                outerRadii,
                null,
                innerRadii
            )
        )
        backgroundShape.paint.color = color // background color
        backgroundShape.paint.style = Paint.Style.FILL // Define background
        backgroundShape.paint.isAntiAlias = true

        val drawables = arrayOf<Drawable>(
            borderDrawable,
            backgroundShape
        )

        val layerDrawable = LayerDrawable(drawables)

        // Horizontal: dim1 - width, dim2 - height
        // Vertical: dim2 - width, dim1 - height
        val t0 = View(binding.cropMainHandler.context)
        t0.x = stX + stWid / 2 - dim1 / 2
        t0.y = stY - dim2 / 2
        t0.layoutParams = ViewGroup.LayoutParams(dim1, dim2)
        binding.cropMainHandler.addView(t0)

        val t1 = View(binding.cropMainHandler.context)
        t1.x = stX + stWid / 2 - dim1 / 2
        t1.y = stY - dim2 / 2 + stHei
        t1.right = t1.x.toInt() + dim1
        t1.bottom = t1.y.toInt() + dim2
        t1.layoutParams = ViewGroup.LayoutParams(dim1, dim2)
        binding.cropMainHandler.addView(t1)

        val t2 = View(binding.cropMainHandler.context)
        t2.x = stX - dim2 / 2
        t2.y = stY + stHei / 2 - dim1 / 2
        t2.right = t2.x.toInt() + dim2
        t2.bottom = t2.y.toInt() + dim1
        t2.layoutParams = ViewGroup.LayoutParams(dim2, dim1)
        binding.cropMainHandler.addView(t2)

        val t3 = View(binding.cropMainHandler.context)
        t3.x = stX - dim2 / 2 + stWid
        t3.y = stY + stHei / 2 - dim1 / 2
        t3.right = t3.x.toInt() + dim2
        t3.bottom = t3.y.toInt() + dim1
        t3.layoutParams = ViewGroup.LayoutParams(dim2, dim1)
        binding.cropMainHandler.addView(t3)

        t0.background = layerDrawable
        t1.background = layerDrawable
        t2.background = layerDrawable
        t3.background = layerDrawable

        ticks.add(SideTick(t0, SideTick.VERTICAL, 0))
        ticks.add(SideTick(t1, SideTick.VERTICAL, 1))
        ticks.add(SideTick(t2, SideTick.HORIZONTAL, 2))
        ticks.add(SideTick(t3, SideTick.HORIZONTAL, 3))

//        emit(MoveTick(0, t0, t0.y))
//        emit(MoveTick(1, t1, t1.y))
//        emit(MoveTick(2, t2, t2.x))
//        emit(MoveTick(3, t3, t2.x))
    }
}