package kennel.pheditor.title

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kennel.pheditor.R

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        val infoButton : Button = findViewById(R.id.button)
    }
}
