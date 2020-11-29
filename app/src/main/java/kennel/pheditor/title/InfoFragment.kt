package kennel.pheditor.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kennel.pheditor.R
import kennel.pheditor.databinding.InfoFragmentBinding

class InfoFragment: Fragment() {
    private lateinit var binding: InfoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.info_fragment,
            container,
            false
        )

        return binding.root
    }
}