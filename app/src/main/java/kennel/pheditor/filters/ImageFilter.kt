package kennel.pheditor.filters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.R
import kennel.pheditor.databinding.CropFragmentBinding
import kennel.pheditor.databinding.ImageFilterBinding

class ImageFilter: Fragment(R.layout.image_filter) {

    private lateinit var binding: ImageFilterBinding
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
        binding.imageView2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guitar))
        return binding.root
    }

    private fun f_light(img: Bitmap) {
        val pix1 = IntArray(img.width * img.height)
        val pix2 = IntArray(img.width * img.height)
        img.getPixels(pix1, 0, img.width, 0, 0, img.width, img.height)
        var r: Int
        var g: Int
        var b: Int
        for (i in 0..pix1.size) {
            r = pix1[i] / (256 * 256)
            g = pix1[i] % (256 * 256) / 256
            b = pix1[i] % 256
        }
    }
}