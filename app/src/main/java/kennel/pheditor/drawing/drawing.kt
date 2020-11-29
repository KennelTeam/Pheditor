package kennel.pheditor.drawing

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.DrawableHelper
import kennel.pheditor.GlobalVars
import kennel.pheditor.R
import kennel.pheditor.databinding.DrawingFragmentBinding


var lastX: Float = 0.toFloat();
var lastY: Float = 0.toFloat();

class DrawingFragment : Fragment() {
    private lateinit var binding: DrawingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.drawing_fragment,
            container,
            false
        )


        val bton: ImageView = binding.imageView
        //bmap = (bton.getDrawable() as BitmapDrawable).bitmap
        binding.imageView.setImageDrawable(GlobalVars.image)
        bton?.setOnTouchListener { v, event ->  onTouchEvent(event)}

        return binding.root
    }


    fun onTouchEvent(e: MotionEvent): Boolean {
        val bton: ImageView = binding.imageView
        val paint: Paint = Paint()
        paint.setColor(Color.GREEN) // установим зеленый цвет
        paint.setStyle(Paint.Style.FILL)
        paint.strokeWidth = 10.toFloat()
        if (e.action == 0) {
            lastX = e.x - bton.x
            lastY = e.y - bton.y// - bton.height / 4
        }

        val bitmap = DrawableHelper.drawableToBitmap(GlobalVars.image)!!.copy(Bitmap.Config.ARGB_8888, true)

        val cvas = Canvas(bitmap)
        cvas.drawLine(lastX, lastY,e.x - bton.x, e.y - bton.y, paint)

        
        Log.i("TEST", e.x.toString() + " " + e.y.toString() + " " + lastX.toString() + " " + lastY.toString())

        lastX = e.x - bton.x
        lastY = e.y - bton.y
        //cvas.drawCircle(e.x - bton.x, e.y - bton.y - bton.height / 2, 10.toFloat(), paint)
        //GlobalVars.image!!.draw(cvas)

        GlobalVars.image = BitmapDrawable(resources, bitmap)
        binding.imageView.setImageDrawable(GlobalVars.image)

        return true
    }
}