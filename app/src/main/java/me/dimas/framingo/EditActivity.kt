package me.dimas.framingo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_edit.*
import me.dimas.framingo.view.CustomView

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Todo [DONE] : before set content view try to pass the image into custom view
        // Retrieve the URI from key IMAGES
        val imageURI = intent.getStringExtra("IMAGES").toUri()
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageURI)
        // Todo : set the bitmap into custom view


        // Setting layout used for this activity
        setContentView(R.layout.activity_edit)

        temp.setImageURI(imageURI)
    }


}
