package me.dimas.framingo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Todo : before set content view try to pass the image into custom view

        // Setting layout used for this activity
        setContentView(R.layout.activity_edit)

        val stringURI = intent.getStringExtra("IMAGES")

        temp.setImageURI(stringURI.toUri())
    }


}
