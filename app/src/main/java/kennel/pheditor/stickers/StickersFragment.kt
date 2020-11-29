package kennel.pheditor.stickers

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.R
import kennel.pheditor.databinding.StickersFragmentBinding
import kotlinx.android.synthetic.main.stickers_fragment.*

class StickersFragment: Fragment() {
    private lateinit var binding: StickersFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.stickers_fragment,
            container,
            false
        )

        binding.mainImageView.setImageResource(R.drawable.kennel)

        binding.mainImageView

        addNewSticker(R.drawable.pes_1)
        addNewSticker(R.drawable.pes_2)
        addNewSticker(R.drawable.pes_3)
        addNewSticker(R.drawable.pes_4)
        addNewSticker(R.drawable.pes_5)

        return binding.root
    }


    private fun addNewSticker(img: Int) {
        val imageView = ImageView(this.context)
        imageView.layoutParams =
                LinearLayout.LayoutParams(150, 150)
//                LinearLayout.LayoutParams(binding.scrollLayout.width, binding.scrollLayout.width)
//                binding.stickersLayout.layoutParams
        println("ttttttttttttt  ${binding.stickersLayout.layoutParams.width}     ${binding.stickersLayout.layoutParams.height}")
        imageView.setImageResource(img)
        binding.stickersLayout.addView(imageView)
    }

}