package kennel.pheditor.filters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.set
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.DrawableHelper
import kennel.pheditor.GlobalVars
import kennel.pheditor.R
import kennel.pheditor.databinding.ImageFilterBinding
import kotlin.math.min

class ImageFilter: Fragment(R.layout.image_filter) {

    private lateinit var binding: ImageFilterBinding
    private var bitmap: Bitmap? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.image_filter,
            container,
            false
        )
        bitmap = DrawableHelper.drawableToBitmap(GlobalVars.image)!!
        bitmap = bitmap!!.copy(Bitmap.Config.ARGB_8888, true)
        binding.imageView2.setImageBitmap(bitmap)

        var button_negative : Button = binding.buttonF1

        button_negative.setOnClickListener{
            f_negative(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }

        var button_change : Button = binding.buttonF2
        button_change.setOnClickListener{
            f_change_c(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }
        return binding.root
    }

    private fun f_negative(img: Bitmap) {
//        val pix1 = IntArray(img.width * img.height)
//        val pix2 = IntArray(img.width * img.height)
//        img.getPixels(pix1, 0, img.width, 0, 0, img.width, img.height)
        var color: Int
        var r: Int
        var g: Int
        var b: Int
        Log.i("qwerty", img.width.toString() + " " + img.height.toString())
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                color = img.getPixel(x, y)
                r = Color.red(color)
                g = Color.green(color)
                b = Color.blue(color)
                img.setPixel(x, y, Color.argb(255, 255 - r, 255 - g, 255 - b))
//                pix2[] = Color.argb(255, 255, 0, 255)
            }
        }
//        img.setPixels(pix2, 0, img.width, 0, 0, img.width, img.height)
    }

    private fun f_change_c(img: Bitmap) {
//        val pix1 = IntArray(img.width * img.height)
//        val pix2 = IntArray(img.width * img.height)
//        img.getPixels(pix1, 0, img.width, 0, 0, img.width, img.height)
        var color: Int
        var r: Int
        var g: Int
        var b: Int
        Log.i("qwerty1", img.width.toString() + " " + img.height.toString())
//        Log.i("HELLO WORLD {}".format(pix1[0]))
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                color = img.getPixel(x, y)
                r = Color.red(color)
                g = Color.green(color)
                b = Color.blue(color)
                img.setPixel(x, y, Color.argb(255, g, b, r))
//                pix2[] = Color.argb(255, 255, 0, 255)
            }
        }
//        img.setPixels(pix2, 0, img.width, 0, 0, img.width, img.height)
    }
}