package later.corporation.adliraihan.gmaker

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_failed.*
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
        loginbtn_intent.setTextColor(resources.getColor(R.color.colorAccent_3))
        var LURD = getSharedPreferenceforWorld(this)
        username_Input.setText(LURD)
        loginbtn_intent.setOnClickListener {
            if(!username_Input.text.isEmpty() &&
                            !password_Input.text.isEmpty())
            {
                loginbtn_intent.apply {
                    setText(resources.getString(R.string.login_onsubmit_loading))
                }
                InitializeLogin(password_Input.text.toString(),this)
            }
        }
        daftar_txt_btn.setOnClickListener(){
            var InterGlobal = Intent(this, DaftarActivity::class.java)
            startActivity(InterGlobal)
        }
        username_Input.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validation()
            }
        })
        password_Input.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validation()
            }
        })
    }
    fun sharedPreferenceForWorld(){
        val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = getSharedPref.edit()
        editor.putString("username",username_Input.text.toString())
        editor.commit()
    }
    fun getSharedPreferenceforWorld(context:Context):String?{
        var getPref = PreferenceManager.getDefaultSharedPreferences(context)
        return getPref.getString("username",null)
    }

    fun validation(){
        var prank = resources.getColor(R.color.colorAccent_2)
        var colorDefault_1  = prank
        if(!username_Input.text.isEmpty() &&
                !password_Input.text.isEmpty())
                    loginbtn_intent.setTextColor(resources.getColor(R.color.colorAccent_2))
                         else
                            loginbtn_intent.setTextColor(resources.getColor(R.color.colorDawn))
    }


    fun InitializeLogin(PassUser:String,asConte:Context){
        database_function.coreURL.url_login += "?var1=" +
                username_Input.text.toString() +
                "&var2=" +
                password_Input.text.toString()
        println("URL : " + database_function.coreURL.url_costum)
        database_function.databaseConn.okRequest = Request.Builder()
                .url(database_function.coreURL.url_login)
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
                    loginbtn_intent.apply {
                        setText(resources.getString(R.string.login_label))
                    }
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
                    var jisonData = JSONArray(response?.body()?.string())
                    try{
                        var Reader = jisonData.getJSONObject(0)
                        println(Reader.toString())
                        datatype_1 = Reader.getString("username")
                        datatype_2 = Reader.getString("password")
                        if(datatype_2.equals(PassUser)){
                            var InterGlobal = Intent(applicationContext, MainActivity::class.java)
                            sharedPreferenceForWorld()
                            InterGlobal.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(InterGlobal)
                        }else{
                            var dialogError = Dialog(asConte)
                                    .apply {
                                setContentView(R.layout.login_failed)
                                logs_error.setText(resources.getString(R.string.login_popup_failed_txt_1 ) + " " + datatype_1)
                                show()
                                tryAgain_btn
                                        .setOnClickListener{
                                            loginbtn_intent.apply {
                                                setText(resources.getString(R.string.login_label))
                                            }
                                            hide()
                                        }
                            }
                        }

                    }
                    catch (E:Exception){
                        println(E)
                        loginbtn_intent.apply {
                            setText(resources.getString(R.string.login_label))
                        }
                        database_function.coreURL.url_costum = database_function.coreURL.url
                    }
                }
            }
        })
    }
}

