package kennel.pheditor.title

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kennel.pheditor.GlobalVars
import kennel.pheditor.R
import kennel.pheditor.databinding.ChooseInstrumentFragmentBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


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

        val saveButton : Button = binding.saveButton
        saveButton.setOnClickListener {
            if(GlobalVars.image != null){
                try {
                    val infoToast : Toast = Toast.makeText(context, "Saving image...", Toast.LENGTH_LONG)
                    infoToast.show()
                    val bitmap : Bitmap = drawableToBitmap(GlobalVars.image)!!
                    saveImage(bitmap, resources.getString(R.string.folder_name))
                    val successToast : Toast = Toast.makeText(context, "Image successfully saved", Toast.LENGTH_LONG)
                    successToast.show()
                } catch (ex : IOException){
                    val exceptionToast : Toast = Toast.makeText(context, "Some exception occurred :(", Toast.LENGTH_LONG)
                    exceptionToast.show()
                    Log.i("exception_qwer", ex.message!!)
                }

            } else {
                nullImageWarning()
            }
        }

        return binding.root
    }

    /// @param folderName can be your app's name
    private fun saveImage(bitmap: Bitmap, folder : String) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            val values = contentValues()
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + folder)
            values.put(MediaStore.Images.Media.IS_PENDING, true)
            // RELATIVE_PATH and IS_PENDING are introduced in API 29.

            val uri: Uri? = context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                saveImageToStream(bitmap, context?.contentResolver?.openOutputStream(uri))
                values.put(MediaStore.Images.Media.IS_PENDING, false)
                context?.contentResolver?.update(uri, values, null, null)
            }
        } else {
            val directory = File(Environment.getExternalStorageDirectory().toString() + "/" + folder)
            // getExternalStorageDirectory is deprecated in API 29

            if (!directory.exists()) {
                directory.mkdirs()
            }
            val fileName = System.currentTimeMillis().toString() + ".png"
            val file = File(directory, fileName)
            saveImageToStream(bitmap, FileOutputStream(file))
            if (file.absolutePath != null) {
                val values = contentValues()
                values.put(MediaStore.Images.Media.DATA, file.absolutePath)
                // .DATA is deprecated in API 29
                context?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            }
        }
    }

    private fun contentValues() : ContentValues {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        return values
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun drawableToBitmap(drawable: Drawable?) : Bitmap? {
        if(drawable != null) {
            var bitmap: Bitmap? = null

            if (drawable is BitmapDrawable) {
                val bitmapDrawable = drawable
                if (bitmapDrawable.bitmap != null) {
                    return bitmapDrawable.bitmap
                }
            }

            bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
                Bitmap.createBitmap(
                    1,
                    1,
                    Bitmap.Config.ARGB_8888
                ) // Single color bitmap will be created of 1x1 pixel
            } else {
                Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
            }

            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
            drawable.draw(canvas)
            return bitmap
        } else {
            return null
        }
    }

    private fun nullImageWarning(){
        val infoToast : Toast = Toast.makeText(context, R.string.null_image_warning, Toast.LENGTH_LONG)
        infoToast.show()
    }

    private fun changeFragment(actionId : Int){
        if(GlobalVars.image != null){
            findNavController().navigate(actionId)
        } else {
            nullImageWarning()
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