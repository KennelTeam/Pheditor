package kennel.pheditor.screens.crop

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.R
import kennel.pheditor.databinding.CropFragmentBinding

class CropFragment: Fragment() {
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
        mainSetup()
        return binding.root
    }

    private fun mainSetup() {
        val clip = ClipData.newPlainText("text1", "text2")
        binding.saveAndBack.setOnDragListener { v, event ->
            Log.i("Nnn", "Wants to drag")
            true
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.saveAndBack.startDragAndDrop(clip, View.DragShadowBuilder(binding.saveAndBack), null, 0)
            Log.i("Nnn", "first started")
        } else {
            binding.saveAndBack.startDrag(clip, View.DragShadowBuilder(binding.saveAndBack), 0, 0)
            Log.i("Nnn", "second started")
        }
    }
}