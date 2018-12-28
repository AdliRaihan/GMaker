package later.corporation.adliraihan.gmaker.adapter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_landing_recycler_child_holder.*
import later.corporation.adliraihan.gmaker.LandingActivity
import later.corporation.adliraihan.gmaker.R
import later.corporation.adliraihan.gmaker.database_function
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}
