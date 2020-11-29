package kennel.pheditor.screens.crop

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.R
import kennel.pheditor.databinding.CropFragmentBinding
import kotlin.math.floor

class CropFragment: Fragment() {
    private lateinit var binding: CropFragmentBinding
    private val mover = Mover()

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
        mainSetup()
        return binding.root
    }

    private fun mainSetup() {
        val clip = ClipData.newPlainText("text1", "text2")

        val s = SideTick(binding.saveAndBack, SideTick.VERTICAL, 0)
    }

    inner class Mover {

    }
}