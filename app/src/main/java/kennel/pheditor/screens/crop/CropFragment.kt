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

    @SuppressLint("ClickableViewAccessibility")
    private fun mainSetup() {
        val clip = ClipData.newPlainText("text1", "text2")
        binding.saveAndBack.setOnDragListener { v, event ->
            Log.i("Nnn", "Wants to drag")
            true
        }
        binding.saveAndBack.setOnTouchListener { v, event ->
            mover.procMove(v, event)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.saveAndBack.startDragAndDrop(clip, View.DragShadowBuilder(binding.saveAndBack), null, 0)
            Log.i("Nnn", "first started")
        } else {
            binding.saveAndBack.startDrag(clip, View.DragShadowBuilder(binding.saveAndBack), 0, 0)
            Log.i("Nnn", "second started")
        }
    }

    inner class Mover {
        private var dl = 0
        private var dt = 0
        private var dr = 0
        private var db = 0

        fun procMove(view: View, event: MotionEvent): Boolean {
            Log.i("Nnn", "asd")
            if (event.action == MotionEvent.ACTION_DOWN) {
                dl = floor(event.rawX - view.left).toInt()
                dt = floor(event.rawY - view.top).toInt()
                dr = floor(event.rawX - view.right).toInt()
                db = floor(event.rawY - view.bottom).toInt()

                Log.i("Nnab", "Deltas: $dr, $dt")
                Log.i("Nnabc", "Constrs: ${view.right - view.left}, ${view.bottom - view.top}")
            } else if (event.action == MotionEvent.ACTION_MOVE) {
                Log.i("Nnab", "Event: ${event.rawX}, ${event.rawY}")
                view.left = floor(event.rawX - dl).toInt()
                view.top = floor(event.rawY - dt).toInt()
                view.right = floor(event.rawX - dr).toInt()
                view.bottom = floor(event.rawY - db).toInt()
            }
            Log.i("Nnab", "$event")
            return true
        }
    }
}