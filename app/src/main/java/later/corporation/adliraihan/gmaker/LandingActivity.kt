package later.corporation.adliraihan.gmaker

import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_landing_recycler_child_holder.*
import kotlinx.android.synthetic.main.activity_landing_recycler_parent.*
import later.corporation.adliraihan.gmaker.adapter.MyRecyclerAdapter
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.security.Policy
import java.util.concurrent.TimeUnit

class LandingActivity : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_recycler_parent)
        ScrollBarLandingUserLayout.smoothScrollTo(0,0)
        run(database_function.coreURL.url_showagenda)
    }

    fun run(baseUri:String) : String {
        var okHetepe = OkHttpClient()
            .newBuilder()
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .build()
        val request = Request.Builder().url(baseUri).build()
        okHetepe.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread{
                    userInteraction.FailedInit = true
                    onUserInteraction()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if(!response.isSuccessful){
                    throw IOException("Unexpected code " + response)
                }
                runOnUiThread {
                    userInteraction.FailedInit = false
                    var policyAllowed = StrictMode.ThreadPolicy.Builder().permitAll().build()
                    StrictMode.setThreadPolicy(policyAllowed)
                    //Policy Added Agar Kita semua Hepi Hepi ajah
                    try{
                        var ArraySuperJudulAgenda = arrayListOf<String>();var jisonJudul:String?
                        var ArraySuperInfoAgenda  = arrayListOf<String>();var jisonDesc:String?
                        var ArraySuperImageLoc = arrayListOf<String>();var jisonImg:String?
                        var ArraySuperId = arrayListOf<String>();var jisonId:String?
                        var responses = response?.body()?.string()
                        var jisonData = JSONArray(responses)
                        try {
                            var Reader = jisonData.getJSONObject(0)
                            var i = 0;
                            do {
                                Reader = jisonData.getJSONObject(i)
                                jisonId = Reader.getString("agenda_id")
                                jisonJudul = Reader.getString("agenda_judul")
                                jisonDesc = Reader.getString("agenda_desc")
                                jisonImg = Reader.getString("agenda_imageloc")
                                val finalocation = baseUri + "/" + jisonImg
                                ArraySuperJudulAgenda.add(jisonJudul)
                                ArraySuperInfoAgenda.add(jisonDesc)
                                ArraySuperImageLoc.add(finalocation)
                                ArraySuperId.add(jisonId)
                                i++
                            } while (i < jisonData.length())
                            viewManager = LinearLayoutManager(applicationContext)
                            viewAdapter = MyRecyclerAdapter(ArraySuperId,ArraySuperJudulAgenda, ArraySuperInfoAgenda, ArraySuperImageLoc,applicationContext)
                            recyclerView = findViewById<RecyclerView>(R.id.RecyclerMe).apply {
                                setHasFixedSize(false)
                                layoutManager = viewManager
                                adapter = viewAdapter
                            }
                        }
                        catch (E:Exception){
                            println(E)
                            Toast.makeText(baseContext,"Could not Refresh the feed",Toast.LENGTH_SHORT).show()
                        }
                    }
                    catch (e:IOException){
                        Toast.makeText(baseContext,"Database Error !",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return "nothing";
    }

    object userInteraction{
        var FailedInit:Boolean? = false
    }
    override fun onUserInteraction() {
        //super.onUserInteraction()
        Toast.makeText(baseContext,"Interaction",Toast.LENGTH_SHORT).show()
        if(userInteraction.FailedInit == true){
            Toast.makeText(baseContext,resources.getString(R.string.error_1_id),Toast.LENGTH_SHORT).show();userInteraction.FailedInit = false
            run(database_function.coreURL.url);
        }
    }

    override fun onUserLeaveHint() {
        //super.onUserLeaveHint()
        userInteraction.FailedInit = false
        Toast.makeText(baseContext,"Leave",Toast.LENGTH_SHORT).show()
    }

}