package kennel.pheditor.drawing

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
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
        binding.imageView?.setOnTouchListener { v, event ->  onTouchEvent(event)}

        return binding.root
    }


    fun onTouchEvent(e: MotionEvent): Boolean {
        val bton: ImageView = binding.imageView
        val paint: Paint = Paint()
        paint.setColor(Color.GREEN) // установим зеленый цвет
        paint.setStyle(Paint.Style.FILL)


        val bitmap = DrawableHelper.drawableToBitmap(GlobalVars.image)!!.copy(Bitmap.Config.ARGB_8888, true)

        paint.strokeWidth = 10.toFloat() * bitmap.width / binding.imageView.width

        var ratioBitmap = bitmap.width.toFloat() / bitmap.height.toFloat()
        var ratioImage = binding.imageView.width.toFloat() / binding.imageView.height.toFloat()

        var x = 0f
        var y = 0f
        var width = 0f
        var height = 0f

        if(ratioBitmap > ratioImage){
            x = 0f
            y = (binding.imageView.height - bitmap.height * bitmap.width.toFloat() / binding.imageView.width) / 2

            width = binding.imageView.width.toFloat()
            height = binding.imageView.height * bitmap.width.toFloat() / binding.imageView.width

        } else{
            x = (binding.imageView.width - bitmap.width * bitmap.height.toFloat() / binding.imageView.height) / 2
            y = 0f

            width = binding.imageView.width * bitmap.height.toFloat() / binding.imageView.height
            height = binding.imageView.height.toFloat()
        }

        val finishX = (e.getX() - x) * bitmap.width.toFloat() / width
        val finishY = (e.getY() - y) * bitmap.height.toFloat() / height

        if (e.action == 0) {
            lastX = finishX
            lastY = finishY
        }

        val cvas = Canvas(bitmap)

        cvas.drawLine(lastX, lastY,finishX, finishY, paint)

        Log.i("TEST",(binding.root.width / binding.imageView.width).toString() )
        Log.i("TEST", finishX.toString() + " " + finishY.toString() + " " + e.getX().toString() + " " + e.getY().toString())

        lastX = finishX
        lastY = finishY
        //cvas.drawCircle(e.x - bton.x, e.y - bton.y - bton.height / 2, 10.toFloat(), paint)
        //GlobalVars.image!!.draw(cvas)

        GlobalVars.image = BitmapDrawable(resources, bitmap)
        binding.imageView.setImageDrawable(GlobalVars.image)

        return true
    }
}