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
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var Id = getBundles()
        setImage(getBundlesImages())
        getData(LandingActivity.baseUrl.best_uri + "/server_config/selected.php?id=" + Id)
    }
    fun getBundles(): String{
        var xBundles = getIntent().extras
        return xBundles.getString("agenda_id")
    }
    fun getBundlesImages(): String{
        var xBundles = getIntent().extras
        return xBundles.getString("agenda_img")
    }
    fun getData(urlSelector:String){
        var detailsHttp = OkHttpClient()
        val request = Request.Builder().url(urlSelector).build()
        detailsHttp.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(applicationContext,"Someting Went Wrong",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                var responses = response?.body()?.string();var jisonJudul:String?;var jisonDesc:String?;var jisonImg:String?
                var jisonSelect = JSONArray(responses);val opt = RequestOptions().centerCrop()
                try{
                    var toRead = jisonSelect.getJSONObject(0)
                    jisonJudul = toRead.getString("agenda_judul")
                    jisonDesc = toRead.getString("agenda_desc")
                    textKu.setText(jisonJudul)
                    text_detail_info.setText(jisonDesc)
                }
                catch (E:Exception){
                    println(E)
                }

            }

        })
    }
    fun setImage(url:String){
        Glide.with(applicationContext).asBitmap().load(url).into(imageHolder_details)
       // Toast.makeText(this,"URL : " + url,Toast.LENGTH_SHORT).show()
    }
}
