package kennel.pheditor.title

import android.os.Bundle
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.*
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import kennel.pheditor.R
import kennel.pheditor.databinding.ChooseInstrumentFragmentBinding

class ChooseInstrumentFragment: Fragment() {
    private lateinit var binding: ChooseInstrumentFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        print("There")
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.choose_instrument_fragment,
            container,
            false
        )

        val img_pick_btn : Button = binding.imgPickBtn

        img_pick_btn.setOnClickListener {
            pickImage()
        }

        val cropButton : ImageButton = binding.cropButton
        cropButton.setOnClickListener {
            findNavController().navigate(R.id.action_chooseInstrument_to_cropFragment)
        }


        return binding.root
    }

    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            binding.imageView.setImageURI(data?.data)
            val a : Toast = Toast.makeText(context, "Image was successfully uploaded!", Toast.LENGTH_LONG)
        }
    }
}