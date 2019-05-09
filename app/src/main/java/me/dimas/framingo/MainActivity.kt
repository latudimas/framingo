package me.dimas.framingo

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val IMAGES = "me.dimas.framingo.IMAGES"

    private val GALLERY_REQUEST_CODE: Int = 1
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Timber Init
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Calling another function
        requestPermission()
        initializeUi()
    }

    /**
     * Initialize UI
     */
    private fun initializeUi() {

        // Set On click event listener for the buttons
        select_button.setOnClickListener {
            openGallery()
//            startEditActivity()

        }
    }

    /**
     * Checking Storage Permission
     */
    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_REQUEST_CODE
            )
        }
    }

    /**
     * Response of permission request result
     * for more info: https://bit.ly/2LVsbfj
     */
//    private fun onRequestPermissionResult(requestCode: Int, permission: Array<String>, grantResults: IntArray) {
//        when (requestCode) {
//            GALLERY_REQUEST_CODE -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // Permission was granted, do proceed to next step
//                } else {
//                    // Permission denied, disable the functionality
//                }
//                return
//            }
//
//            // Add other 'when' lines to check for other permission this app might request
//            else -> {
//                // Ignore all other request
//            }
//        }
//    }

    /**
     * Intent for running Gallery Apps
     */
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    /**
     * Result after calling intent
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            val imageURI = data.data
            val stringURI = imageURI.toString()
            val imagePath = imageURI.path

            Timber.d("Image Path: $imagePath")
            Timber.d("Image URI: $imageURI")

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageURI)
//                image_display.setImageURI(contentURI)
//                custom_display.getBitmap(applicationContext, bitmap)
//                image.setImageBitmap(bitmap)
//                startEditActivity(stringURI)
                image.setImageBitmap(bitmap)
                createTempImage(bitmap, stringURI)
            } catch (e: IOException) {
                e.printStackTrace()
                Timber.e("IO EXCEPTION $e")
            }
        }
    }

    /**
     * Start Editing Activity
     */
    private fun startEditActivity(stringURI: String) {
        val intent = Intent(this@MainActivity, EditActivity::class.java).apply {
            putExtra("IMAGES", stringURI)
        }
        startActivity(intent)
    }

    private fun createTempImage(imageBitmap: Bitmap, uri: String) {
        // Todo create temp image file
//        val fileName = "selected_image"
//        val outputDir = applicationContext.cacheDir
//        File.createTempFile(fileName, ".jpg", outputDir)

//        Timber.d("Cache Directories: $outputDir")

        Uri.parse(uri).lastPathSegment.let { fileName ->
            File.createTempFile(fileName, ".jpg", applicationContext.cacheDir)
            Timber.d("File Name : $fileName")
            Timber.d("Cache Dir : ${applicationContext.cacheDir}")
        }

        // Todo Make sure temporary data created using Device File Explorer
        // Todo craete methods for passing the file name into Custom Activity\
    }
}
