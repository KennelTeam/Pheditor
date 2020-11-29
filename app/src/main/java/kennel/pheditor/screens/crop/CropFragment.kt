package kennel.pheditor.screens.crop

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.util.Log
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.core.view.marginLeft
import androidx.core.view.marginRight

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.GlobalVars
import kennel.pheditor.R
import kennel.pheditor.databinding.CropFragmentBinding
import kotlin.math.floor

class CropFragment : Fragment() {
    private lateinit var binding: CropFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.crop_fragment,
            container,
            false
        )
        binding.frontImage.setImageDrawable(GlobalVars.image)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("Ratio", "ddssd")

        binding.frontImage.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                Log.i("Ratio", "started")
                val imgRatio =
                    GlobalVars.image!!.bounds.right.toFloat() / GlobalVars.image!!.bounds.bottom.toFloat()
                Log.i("Ratio", "$imgRatio")

                val wiView = binding.frontImage.measuredWidth.toFloat()
                val heView = binding.frontImage.measuredHeight.toFloat()
                val viewRatio = wiView.toFloat() / heView.toFloat()
                Log.i("Ratio", "$wiView, $heView")
                Log.i("Ratio", "$viewRatio")

                val viewX: Float
                val viewY: Float
                val viewWidth: Float
                val viewHeight: Float
                if (imgRatio >= viewRatio) {
                    viewX = 0f
                    viewWidth = wiView
                    viewHeight = viewWidth / imgRatio
                    viewY = heView / 2 - viewHeight / 2
                } else {
                    viewY = 0f
                    viewHeight = heView
                    viewWidth = viewHeight * imgRatio
                    viewX = wiView / 2 - viewWidth / 2
                }

                val manager = TicksManager(
                    binding.cropMainHandler.context, binding,
                    viewX+binding.frontImage.marginLeft+binding.frontImage.marginRight, viewY+binding.spacer.layoutParams.height,
                    viewWidth, viewHeight,
                    200, 20, R.color.mainTextColor
                )
                binding.frontImage.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}