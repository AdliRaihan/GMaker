package later.corporation.adliraihan.gmaker.firebase

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_landing_recycler_parent_update.*
import kotlinx.android.synthetic.main.activity_landing_recycler_parent_update.view.*
import kotlinx.android.synthetic.main.child_activity_landing_menu.view.*
import later.corporation.adliraihan.gmaker.LoginActivity
import later.corporation.adliraihan.gmaker.MainActivity
import later.corporation.adliraihan.gmaker.R
import later.corporation.adliraihan.gmaker.adapter.MyRecyclerAdapter
import later.corporation.adliraihan.gmaker.adapter.MyRecyclerAdapterOngoing
import java.text.SimpleDateFormat
import java.util.*

class FirebaseLandingActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_recycler_parent_update)
        initLanding()
        scroller_landing.smoothScrollTo(0,0)
        val menuDrawer = LayoutInflater.from(applicationContext).inflate(R.layout.child_activity_landing_menu,landingHeader,false)
        menu_drawer.setOnClickListener{
            doOpen(menuDrawer,landingHeader)
            menu_drawer.isEnabled = false
        }
        quick_create_agenda.setOnClickListener{
            var InterGlobal = Intent(applicationContext, FirebaseCreateActivity::class.java)
            startActivity(InterGlobal)
        }
        initializeAgenda()
        OngoingRightnow()
    }

    fun initializeAgenda(){
        var Pref = Database.variables.myRead.child("user_" + username_logged.text.toString() + Database.url.user_agenda )
        val functionImplements = Pref.orderByChild("title")
        functionImplements.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                    val children = p0.children
                println("Count : " + p0.children.count())
                totalagendacount.setText(p0.children.count().toString())
                if(p0.children.count() == 0 ){
                    bottomRecycler.visibility = View.GONE
                    landing_isnull.visibility = View.VISIBLE
                }else{
                    bottomRecycler.visibility = View.VISIBLE
                    landing_isnull.visibility = View.GONE

                    //VARIABLES//
                    var agenda_judul = arrayListOf<String>()
                    var agenda_desk = arrayListOf<String>()
                    var agenda_type = arrayListOf<String>()
                    var agenda_time = arrayListOf<String>()
                    var agenda_date = arrayListOf<String>()

                    //END VAR//
                    children.forEach {
                        agenda_judul.add(it.child("title").value.toString())
                        agenda_desk.add(it.child("description").value.toString())
                        agenda_time.add(it.child("time").value.toString())
                        agenda_date.add(it.child("date").value.toString())
                        agenda_type.add(it.child("type").value.toString())
                    }

                    viewManager = LinearLayoutManager(this@FirebaseLandingActivity,LinearLayout.HORIZONTAL,false)
                    viewAdapter = MyRecyclerAdapter(agenda_judul,agenda_type,agenda_time,agenda_date,agenda_desk,this@FirebaseLandingActivity)
                    recyclerView = findViewById<RecyclerView>(R.id.bottomRecycler).apply {
                        setHasFixedSize(false)
                        layoutManager = viewManager
                        adapter = viewAdapter
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
        var strFormat = df.format(getT)

        var tf = SimpleDateFormat("hh:mm a")
        var gtf = tf.format(getT)
        if((hours > 6 && hours < 10)
                &&
                (timeinfo.equals("AM"))
        ){
            status_info.setText("Selamat pagi !")
        }else if((hours >= 10 && hours < 12)
                &&
                (timeinfo.equals("AM"))
        ){
            status_info.setText("Selamat Siang !")
        }else if((hours >= 12 && hours < 5)
                &&
                (timeinfo.equals("PM"))
        ){
            status_info.setText("Selamat sore !")
        }else if((hours >= 5 && hours < 12)
                &&
                (timeinfo.equals("PM"))
        ){
            status_info.setText("Selamat Malam !")
        }else{
            status_info.setText("Dini hari !")
        }
        println(hours.toString() + " " + timeinfo)

        var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        username_logged.setText(setUser)
    }

    //=========== MENU DRAWER ==============
    fun doOpen(
            draweropn: View,
            parent: RelativeLayout
    ){
        runOnUiThread {
            fun nothing(){
                parent.removeView(draweropn)
                parent.menu_drawer.isEnabled = true;
            }
            if(draweropn.isEnabled)
                parent.addView(draweropn)

            draweropn.close_drawer_btn.setOnClickListener{nothing()}
            draweropn.create_gendabtn.setOnClickListener{nothing();doChildOpen("create")}
            draweropn.account_gendabtn.setOnClickListener{nothing();doChildOpen("account")}
            draweropn.logout_gendabtn.setOnClickListener{nothing();doChildOpen("logout")}
        }
    }
    fun doChildOpen(arts:Any){
        when(arts){
            "create"->{
                var InterGlobal = Intent(applicationContext, FirebaseCreateActivity::class.java)
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

    fun OngoingRightnow(){
        var Pref = Database.variables.myRead.child("user_" + username_logged.text.toString() + Database.url.user_agenda )
        val functionImplements = Pref.orderByChild("title")
        functionImplements.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val children = p0.children
                println("Count : " + p0.children.count())
                totalagendacount.setText(p0.children.count().toString())
                if(p0.children.count() == 0 ){
                    bottomRecycler.visibility = View.GONE
                    landing_isnull.visibility = View.VISIBLE
                }else{
                    bottomRecycler.visibility = View.VISIBLE
                    landing_isnull.visibility = View.GONE

                    //VARIABLES//
                    var agenda_judul = arrayListOf<String>()
                    var agenda_desk = arrayListOf<String>()
                    var agenda_type = arrayListOf<String>()
                    var agenda_time = arrayListOf<String>()
                    var agenda_date = arrayListOf<String>()

                    //END VAR//
                    children.forEach {
                        agenda_judul.add(it.child("title").value.toString())
                        agenda_desk.add(it.child("description").value.toString())
                        agenda_time.add(it.child("time").value.toString())
                        agenda_date.add(it.child("date").value.toString())
                        agenda_type.add(it.child("type").value.toString())
                    }

                    viewManager = LinearLayoutManager(this@FirebaseLandingActivity)
                    viewAdapter = MyRecyclerAdapterOngoing(agenda_judul,agenda_type,agenda_time,agenda_date,agenda_desk,this@FirebaseLandingActivity)
                    recyclerView = findViewById<RecyclerView>(R.id.ongoingRecycler).apply {
                        setHasFixedSize(false)
                        layoutManager = viewManager
                        adapter = viewAdapter
                    }
                }
            }

        })
    }

}