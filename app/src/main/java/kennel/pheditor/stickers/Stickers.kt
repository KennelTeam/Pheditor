package kennel.pheditor.stickers

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.util.Log
import android.view.*

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kennel.pheditor.R
import kennel.pheditor.databinding.StickersBinding
import kotlin.math.floor

class Stickers: Fragment() {
    private lateinit var binding: StickersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.stickers,
            container,
            false
        )

        return binding.root
    }
}