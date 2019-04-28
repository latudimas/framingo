package me.dimas.framingo

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val OPEN_GALLERY_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()
        initializeUi()
    }

    /**
     * Initialize UI
     */
    private fun initializeUi() {
        hint_text_display.text = "Click the Image Above"

        // Set on click on image
        image_display.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            openGallery()
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
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), OPEN_GALLERY_REQUEST_CODE
            )
        }
    }

    /**
     * Response of permission request result
     * for more info: https://bit.ly/2LVsbfj
     */
    private fun onRequestPermissionResult(requestCode: Int, permission: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            OPEN_GALLERY_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted, do proceed to next step
                } else {
                    // Permission denied, disable the functionality
                }
                return
            }

            // Add other 'when' lines to check for other permission this app might request
            else -> {
                // Ignore all other request
            }
        }
    }

    /**
     * Intent for running Gallery Apps
     */
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, OPEN_GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            val contentURI = data.data
            hint_text_display.text = contentURI?.toString()
            Picasso.get()
                .load(contentURI)
                .centerCrop()
                .into(image_display)
        }
    }
}
