package later.corporation.adliraihan.gmaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.child_activity_landing_menu.view.*
import kotlinx.android.synthetic.main.child_activity_retryconnection.view.*
import later.corporation.adliraihan.gmaker.firebase.CreateDailyActivity
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class LandingActivity : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    open var DrawerisOpen:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        setContentView(R.layout.activity_landing_recycler_parent_update)
        initLanding()

        val x = LayoutInflater.from(applicationContext).inflate(R.layout.child_activity_retryconnection,landingHeader,false)
        val y = landingHeader
        val menuDrawer = LayoutInflater.from(applicationContext).inflate(R.layout.child_activity_landing_menu,landingHeader,false)
        onRead(database_function.coreURL.url_showagenda,this,x,y)

        menu_drawer.setOnClickListener{
                    doOpen(menuDrawer,landingHeader)
            menu_drawer.isEnabled = false
        }
    }
    fun initLanding(){
        var getT = Calendar.getInstance().time
        var tf = SimpleDateFormat("hh:mm a")
        var gtf = tf.format(getT)
        timerCounter.setText(gtf.toString())
        var df = SimpleDateFormat("dd-mm-yyyy")
        var strFormat = df.format(getT)
        var setUser  = LoginActivity().getSharedPreferenceforWorld(this)
        username_logged.setText(setUser)
        status_info.setText(strFormat)

    }
    fun doOpen(
            draweropn:View,
            parent:RelativeLayout
    ){
        runOnUiThread {
            fun nothing(){
                parent.removeView(draweropn)
                parent.menu_drawer.isEnabled = true;
            }
            if(draweropn.isEnabled)
                parent.addView(draweropn)

            draweropn.close_drawer_btn.setOnClickListener{nothing()}
            draweropn.create_gendabtndaily.setOnClickListener{nothing();doChildOpen("dailycreate")}
            draweropn.create_gendabtn.setOnClickListener{nothing();doChildOpen("create")}
            draweropn.account_gendabtn.setOnClickListener{nothing();doChildOpen("account")}
            draweropn.logout_gendabtn.setOnClickListener{nothing();doChildOpen("logout")}
        }
    }
    fun doChildOpen(arts:Any){
        when(arts){
            "create"->{
                Log.i("Button","DailyCreate tapped")
            }
            "dailycreate"->{
                Log.i("Button","DailyCreate tapped")
                var InterGlobal = Intent(applicationContext, CreateDailyActivity::class.java)
                startActivity(InterGlobal)
            }
            "account"->{

            }
            "logout"->{
                val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this)
                val editor = getSharedPref.edit()
                editor.putString("username",null)
                editor.commit()
                var InterGlobal = Intent(applicationContext, MainActivity::class.java)
                startActivity(InterGlobal)
            }
        }
    }
     override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish()
        }
         return super.onKeyDown(keyCode, event)
     }

    fun toastShort(txt:String,contGiver:Context){
        runOnUiThread {
            Toast.makeText(contGiver,txt,Toast.LENGTH_SHORT).show()
        }
    }
    fun onRead(url:String, myCont:Context, layoutPop: View, header:RelativeLayout){
        database_function.coreURL.url_showagenda = database_function.coreURL.url_defaultagenda
        database_function.coreURL.url_showagenda += LoginActivity().getSharedPreferenceforWorld(myCont)
        println("URL : " + database_function.coreURL.url_showagenda)
        database_function.databaseConn.okHetepe.newCall(Request.Builder().url(database_function.coreURL.url_showagenda).build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    header.addView(layoutPop)
                    layoutPop.retryPopup.setOnClickListener{
                        onRead("",myCont,layoutPop,header)
                        header.removeView(layoutPop)
                    }
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if(!response.isSuccessful){
                    // toastShort("Please contact admin !",myCont)
                }else{
                    runOnUiThread{
                        StrictMode.setThreadPolicy(database_function.databaseConn.policyApp)
                        var agenda_user = arrayListOf<String>()
                        var agenda_judul = arrayListOf<String>()
                        var agenda_desk = arrayListOf<String>()
                        var agenda_type = arrayListOf<String>()
                        var jisonData = JSONArray(response?.body()?.string())
                        var i = 0;
                        try{

                            if(jisonData.length() > 0){

                            do{
                                var Reader = jisonData.getJSONObject(i)
                                agenda_judul.add(Reader.getString("agenda_judul"))
                                agenda_desk.add(Reader.getString("agenda_target"))
                                agenda_type.add(Reader.getString("agenda_type"))
                                println(agenda_judul[i] + "\n" + agenda_desk[i] + "\n" + agenda_type[i])
                                i++
                            }while(i < jisonData.length())

                                viewManager = LinearLayoutManager(myCont)
                               // viewAdapter =MyRecyclerAdapter(agenda_judul,agenda_desk,agenda_type,myCont)
                                recyclerView = findViewById<RecyclerView>(R.id.bottomRecycler).apply {
                                    setHasFixedSize(false)
                                    layoutManager = viewManager
                                    adapter = viewAdapter
                                }
                            }else{

                            }
                        }
                        catch (cepon:Exception){
                            println("ERROR Code  : " + cepon)
                        }
                    }
                }
            }
        })
*/
    }
}
