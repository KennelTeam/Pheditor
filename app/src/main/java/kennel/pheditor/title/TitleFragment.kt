package kennel.pheditor.title

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import kennel.pheditor.R
import kennel.pheditor.databinding.TitleFragmentBinding

class TitleFragment: Fragment() {
    private lateinit var binding: TitleFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        val infoButton : Button = binding.infoButton

        infoButton.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_infoFragment)
        }

        val startButton : Button = binding.startButton

        startButton.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_chooseInstrument)
        }

        return binding.root
    }
}