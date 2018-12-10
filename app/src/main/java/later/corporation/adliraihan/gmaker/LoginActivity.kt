package later.corporation.adliraihan.gmaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import later.corporation.adliraihan.gmaker.adapter.MyRecyclerAdapter
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.io.ObjectInput
import java.lang.Exception
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(){
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val getSharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        var LURD = getSharedPref.getString("username","")
        username_Input.setText(LURD)
        loginbtn_intent.setOnClickListener {
            with(getSharedPref.edit()){
                putString("username",username_Input.text.toString())
                        commit()
            }
            if(!username_Input.text.isEmpty() &&
                            !password_Input.text.isEmpty())
            {
                InitializeLogin()
            }
        }
        daftar_txt_btn.setOnClickListener(){
            var InterGlobal = Intent(this, DaftarActivity::class.java)
            startActivity(InterGlobal)
        }
    }
    fun InitializeLogin(){
        database_function.coreURL.url_costum += "/server_config/server_login.php?var1=" +
                username_Input.text.toString() +
                "&var2=" +
                password_Input.text.toString()
        println("URL : " + database_function.coreURL.url_costum)
        database_function.databaseConn.okRequest = Request.Builder()
                .url(database_function.coreURL.url_costum)
                .build()
        database_function
                .databaseConn
                .okHetepe
                .newCall(database_function.databaseConn.okRequest)
                .enqueue(object  : Callback{
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{
                    Toast.makeText(baseContext
                            ,resources.getString(R.string.error_1_id)
                            ,Toast.LENGTH_LONG)
                            .show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if(!response.isSuccessful){
                    throw IOException("Unexpected code " + response)
                }
                runOnUiThread{
                    var policyAllowed = StrictMode.ThreadPolicy.Builder().permitAll().build()
                    StrictMode.setThreadPolicy(policyAllowed)
                    var datatype_1:String?
                    var datatype_2:String?
                    var responses = response?.body()?.string()
                   //database_function.JSONReader.gendaJSArray = JSONArray(responses)
                    var jisonData = JSONArray(responses)
                   // println("DATABSE : "+ database_function.JSONReader.gendaJSArray)
                    try{
                        var Reader = jisonData.getJSONObject(0)
                        println(Reader.toString())
                        datatype_1 = Reader.getString("username")
                        datatype_2 = Reader.getString("password")
                        Toast.makeText(baseContext,datatype_1,Toast.LENGTH_LONG).show()
                        println("DATAKU : " + datatype_1)
                        /*
                        if(true){
                            var InterGlobal = Intent(baseContext, LandingActivity::class.java)
                            startActivity(InterGlobal)
                        }
                        */
                    }
                    catch (E:Exception){
                        println(E)
                        database_function.coreURL.url_costum = database_function.coreURL.url
                    }


                }
            }
        })
    }


}

