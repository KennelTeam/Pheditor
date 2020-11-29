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
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.set
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.DrawableHelper
import kennel.pheditor.GlobalVars
import kennel.pheditor.R
import kennel.pheditor.databinding.ImageFilterBinding
import kotlin.math.max
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

        val button_negative : Button = binding.buttonF1

        button_negative.setOnClickListener{
            val infoToast : Toast = Toast.makeText(context, R.string.image_handlers, Toast.LENGTH_LONG)
            infoToast.show()
            f_negative(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }

        val button_change : Button = binding.buttonF2
        button_change.setOnClickListener{
            val infoToast : Toast = Toast.makeText(context, R.string.image_handlers, Toast.LENGTH_LONG)
            infoToast.show()
            f_change_c(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }
        val button_dark : Button = binding.buttonF3
        button_dark.setOnClickListener{
            val infoToast : Toast = Toast.makeText(context, R.string.image_handlers, Toast.LENGTH_LONG)
            infoToast.show()
            f_dark(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }
        val button_light : Button = binding.buttonF4
        button_light.setOnClickListener{
            val infoToast : Toast = Toast.makeText(context, R.string.image_handlers, Toast.LENGTH_LONG)
            infoToast.show()
            f_light(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }
        val button_grayscale : Button = binding.buttonF5
        button_grayscale.setOnClickListener{
            val infoToast : Toast = Toast.makeText(context, R.string.image_handlers, Toast.LENGTH_LONG)
            infoToast.show()
            f_grayscale(this.bitmap!!)
            binding.imageView2.setImageBitmap(bitmap)
            GlobalVars.image = this.bitmap!!.toDrawable(resources)

        }
        return binding.root
    }

    private fun f_negative(img: Bitmap) {
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
            }
        }
    }

    private fun f_change_c(img: Bitmap) {
        var color: Int
        var r: Int
        var g: Int
        var b: Int
        Log.i("qwerty1", img.width.toString() + " " + img.height.toString())
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                color = img.getPixel(x, y)
                r = Color.red(color)
                g = Color.green(color)
                b = Color.blue(color)
                img.setPixel(x, y, Color.argb(255, g, b, r))
            }
        }
    }

    private fun f_grayscale(img: Bitmap) {
        var color: Int
        var r: Int
        var g: Int
        var b: Int
        var mid: Int
        Log.i("qwerty1", img.width.toString() + " " + img.height.toString())
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                color = img.getPixel(x, y)
                r = Color.red(color)
                g = Color.green(color)
                b = Color.blue(color)
                mid = (r + g + b) / 3
                img.setPixel(x, y, Color.argb(255, mid, mid, mid))
            }
        }
    }

    private fun f_dark(img: Bitmap) {
        var color: Int
        var r: Int
        var g: Int
        var b: Int
        Log.i("qwerty1", img.width.toString() + " " + img.height.toString())
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                color = img.getPixel(x, y)
                r = Color.red(color)
                g = Color.green(color)
                b = Color.blue(color)
                img.setPixel(x, y, Color.argb(255, max(0, r - 30), max(0, g - 30), max(0, b - 30)))
            }
        }
    }

    private fun f_light(img: Bitmap) {
        var color: Int
        var r: Int
        var g: Int
        var b: Int
        Log.i("qwerty1", img.width.toString() + " " + img.height.toString())
        for (x in 0 until img.width) {
            for (y in 0 until img.height) {
                color = img.getPixel(x, y)
                r = Color.red(color)
                g = Color.green(color)
                b = Color.blue(color)
                img.setPixel(x, y, Color.argb(255, min(255, r + 30), min(255, g + 30), min(255, b + 30)))
            }
        }
    }
}