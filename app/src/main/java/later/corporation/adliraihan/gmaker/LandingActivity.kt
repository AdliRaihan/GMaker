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

class LandingActivity : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    object baseUrl{
        val best_uri = "http://192.168.10.24:81";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_recycler_parent)
        run(baseUrl.best_uri)
        ScrollBarLandingUserLayout.smoothScrollTo(0,0)
    }

    fun run(baseUri:String) : String {
        var okHetepe = OkHttpClient()
        val request = Request.Builder().url(baseUri).build()
        okHetepe.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Toast.makeText(applicationContext,e.toString(),Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call, response: Response) {
                if(!response.isSuccessful){
                    throw IOException("Unexpected code " + response)
                }
                runOnUiThread {
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



        /*
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urls[0])
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonData = responses.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            JSONArray Jarray = Jobject.getJSONArray("employees");

            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object     = Jarray.getJSONObject(i);
            }
        }
        */
        return "nothing";
    }



}