package later.corporation.adliraihan.gmaker.firebase

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_landing_recycler_child_holder_update.*
import kotlinx.android.synthetic.main.activity_landing_recycler_parent.*
import kotlinx.android.synthetic.main.activity_landing_recycler_parent.view.*
import kotlinx.android.synthetic.main.child_activity_landing_menu.view.*
import later.corporation.adliraihan.gmaker.MainActivity
import later.corporation.adliraihan.gmaker.R
import later.corporation.adliraihan.gmaker.adapter.MyRecyclerAdapter
import java.text.SimpleDateFormat
import java.util.*

class FirebaseLandingActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    open lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_recycler_parent)
        initLanding()
        val menuDrawer = LayoutInflater.from(applicationContext).inflate(R.layout.child_activity_landing_menu,GodFatherLandingUserLayout,false)
        menu_drawer.setOnClickListener{
            doOpen(menuDrawer,GodFatherLandingUserLayout)

            menu_drawer.isEnabled = false
        }
        forceCreateAgenda.setOnClickListener{
            finish()
            var InterGlobal = Intent(applicationContext, FirebaseCreateActivity::class.java)
            startActivity(InterGlobal)
        }


        //Anim
        var slideUp_ = AnimationUtils.loadAnimation(this@FirebaseLandingActivity,R.anim.abc_slide_in_top)
        landingBody.startAnimation(slideUp_)
        //anim

        initializeAgenda()
        setBroadcast()
    }
    //BROADCAST//
    var FR = FirebaseReceiver()
    fun setBroadcast(){
        var vrl = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction("android.net.conn.CONNECTIVITY_CHANGE")
        }
        registerReceiver(FR,vrl)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(FR)
    }
    //BROADCAST END
    fun initializeAgenda(){
        if (getMessageFT(this).equals("0")){
            showMessage()
        }

        var Pref = Database.variables.myRead.child("user_" + username_logged.text.toString() + Database.url.user_agenda )
        val functionImplements = Pref.orderByChild("date")
        functionImplements.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                    val children = p0.children
                totalagendacount.setText(p0.children.count().toString())
                if(p0.children.count() == 0 ){
//                    bottomRecycler.visibility = View.GONE
                    landing_isnull.visibility = View.VISIBLE
                }else{
//                    bottomRecycler.visibility = View.VISIBLE
                    landing_isnull.visibility = View.GONE

                    //VARIABLES//
                    var agenda_judulToday = arrayListOf<String>()
                    var agenda_judul = arrayListOf<String>()
                    var agenda_desk = arrayListOf<String>()
                    var agenda_type = arrayListOf<String>()
                    var agenda_time = arrayListOf<String>()
                    var agenda_date = arrayListOf<String>()
                    //END VAR//
                    children.forEach {
                        var CY = Calendar.getInstance().time
                        var CYs = SimpleDateFormat("dd/MMM").format(CY)
                        Handler().postDelayed({
                            agenda_judul.add(it.child("title").value.toString())
                            agenda_desk.add(it.child("description").value.toString())
                            agenda_time.add(it.child("time").value.toString())
                            agenda_date.add(it.child("date").value.toString())
                            agenda_type.add(it.child("type").value.toString())
                            viewManager = LinearLayoutManager(this@FirebaseLandingActivity)
                            viewAdapter = MyRecyclerAdapter(agenda_judul,agenda_type,agenda_date,agenda_desk,this@FirebaseLandingActivity)
                            recyclerView = bottomRecycler.apply {
                                setHasFixedSize(false)
                                layoutManager = viewManager
                                adapter = viewAdapter
                            }
                        },2500)

                        try{
                            if(FirebaseCalendar().getDateMonthFromArray(it.child("date").value.toString()).equals(CYs)) {
                                println("CYS : $CYs : Full Time ${FirebaseCalendar().getDateMonthFromArray(it.child("date").value.toString())}")
                                agenda_judulToday.add(it.child("title").value.toString())
                                RSSPullService().setAvailableSchedule(agenda_judulToday)
                            }
                        }
                        catch (E:java.lang.Exception){
                        }
                    }
                    try{
                    }
                    catch(E:Exception){
                        Log.i("Error Message",E.toString())
                    }
                }
            }

        })
    }

    fun initLanding(){
        var getT = Calendar.getInstance().getTime()
        var yearshs = SimpleDateFormat("yyyy")
        var hoursf = SimpleDateFormat("hh")
        var time = SimpleDateFormat("a")
        var df = SimpleDateFormat("hh:mm")
        var hours = hoursf.format(getT).toInt()
        var timeinfo = time.format(getT)

        headerStatistics.setText("Statistics " + yearshs.format(getT).toString())
        var tf = SimpleDateFormat("hh:mm a")
        var gtf = tf.format(getT)
        if((hours > 6 && hours < 10)
                &&
                (timeinfo.equals("AM"))
        ){
            status_info.setText("Selamat pagi")
        }else if((hours >= 10 && hours < 12)
                &&
                (timeinfo.equals("AM"))
        ){
            status_info.setText("Selamat Siang")
        }else if((hours >= 1 && hours < 5)
                &&
                (timeinfo.equals("PM"))
        ){
            status_info.setText("Selamat sore")
        }else if((hours >= 5 && hours < 1)
                &&
                (timeinfo.equals("PM"))
        ){
            status_info.setText("Selamat Malam")
        }else{
            status_info.setText("Dini hari")
        }

        var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        username_logged.setText("$setUser")
    }

    //=========== MENU DRAWER ==============
    fun doOpen(
            draweropn: View,
            parent: RelativeLayout
    ){
        runOnUiThread {
            fun nothing(){
                draweropn.parentMenuDrawer.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_end))
                Handler().postDelayed({
                    parent.removeView(draweropn)
                },250)
                parent.menu_drawer.isEnabled = true
            }
            if(draweropn.isEnabled){
                parent.addView(draweropn)
                draweropn.parentMenuDrawer.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_up))
            }

            draweropn.apply {
                close_drawer_btn.setOnClickListener {nothing()}
                Authorbtn.setOnClickListener {  nothing();doChildOpen("info")}
                create_gendabtn.setOnClickListener{
                    nothing();doChildOpen("create")
                    it.isEnabled = false
                    Handler().postDelayed({
                        it.isEnabled = true
                    },2000)
                }
                create_gendabtndaily.setOnClickListener{nothing();doChildOpen("createdaily")}
                account_gendabtn.setOnClickListener{nothing();doChildOpen("account")}
                logout_gendabtn.setOnClickListener{nothing();doChildOpen("logout")}
            }
        }
    }
    fun doChildOpen(arts:Any){
        when(arts){
            "info"->{
                Toast.makeText(this,"Dibuat oleh Adli Raihan \n github.com/Thibobs",Toast.LENGTH_SHORT).show()
            }
            "create"->{
                //finish()
                startActivity( Intent(applicationContext, FirebaseCreateActivity::class.java).addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
            "createdaily"->{
                //finish()
                startActivity(Intent(applicationContext, CreateDailyActivity::class.java).addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
            "account"->{
            }
            "logout"->{
                val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this)
                val editor = getSharedPref.edit()
                editor.putString("username",null)
                editor.commit()
                startActivity(Intent(applicationContext, MainActivity::class.java).addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }
    }

    fun showMessage(){
        Log.i("TAG","ShowMessage()")
        notmessage.apply {
                visibility = View.VISIBLE
            closenotificationmessage.setOnClickListener {
                setMessageFT()
                visibility = View.GONE
            }
        }
    }
    fun getMessageFT(context:Context) : String{
        var getPref = PreferenceManager.getDefaultSharedPreferences(context)
        return getPref.getString("notemessage","0")
    }
    fun setMessageFT(){
        val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = getSharedPref.edit()
        editor.putString("notemessage","1")
        editor.commit()
    }
}