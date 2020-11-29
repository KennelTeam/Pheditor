package kennel.pheditor.title

import kennel.pheditor.GlobalVars
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.choose_instrument_fragment,
            container,
            false
        )

        if(GlobalVars.image != null){
            binding.imageView.setImageDrawable(GlobalVars.image)
        }

        val img_pick_btn : Button = binding.imgPickBtn

        img_pick_btn.setOnClickListener {
            pickImage()
        }

        val cropButton : ImageButton = binding.cropButton
        cropButton.setOnClickListener {
            changeFragment(R.id.action_chooseInstrument_to_cropFragment)
        }

        val sticker : ImageButton = binding.pesButton
        sticker.setOnClickListener {
            changeFragment(R.id.action_chooseInstrument_to_stickerFragment)
        }

        val imageFilterButton : ImageButton = binding.filterButton
        imageFilterButton.setOnClickListener {
            changeFragment(R.id.action_chooseInstrument_to_imageFilter)
        }


        return binding.root
    }

    private fun changeFragment(actionId : Int){
        if(GlobalVars.image != null){
            findNavController().navigate(actionId)
        } else {
            val infoToast : Toast = Toast.makeText(context, "You have not uploaded any image!", Toast.LENGTH_LONG)
            infoToast.show()
        }
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
            val infoToast : Toast = Toast.makeText(context, "Image was successfully uploaded!", Toast.LENGTH_LONG)
            infoToast.show()


            if(data != null){
                if(data.data != null){
                    val inputStream =  activity?.contentResolver?.openInputStream(data.data!!)
                    GlobalVars.image = Drawable.createFromStream(inputStream, data.data!!.toString())
                }
            }

        }
    }
}