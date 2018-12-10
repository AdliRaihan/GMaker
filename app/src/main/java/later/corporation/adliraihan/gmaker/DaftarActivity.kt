package later.corporation.adliraihan.gmaker

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.sax.StartElementListener
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class DaftarActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        submit_asDaftar.setOnClickListener {
            var var1 = username_daftar.text.toString();var var2=password_daftar.text.toString()
            var URL_BUILDER = database_function.URLBuilder.url + "var1=" + var1 + "&var2=" + var2
            println(URL_BUILDER)
            Push(URL_BUILDER)
        }
    }
    fun Push(uriInternal: String) {
        val okHtp = database_function.databaseConn.okHetepe
        val request =
                Request.Builder().url(uriInternal).build()
        okHtp.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{
                    Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_LONG).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                var policyAllowed = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policyAllowed)
                runOnUiThread {
                    try {
                        if (response.isSuccessful) {
                            var InterGlobal = Intent(baseContext,LoginActivity::class.java)
                            startActivity(InterGlobal)
                        }
                    } catch (E: Exception) {
                        println("Error")
                        println("ERROR :" + E)
                    }
                }
            }
        })
    }
}