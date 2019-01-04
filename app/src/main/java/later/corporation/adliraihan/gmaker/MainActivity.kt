package later.corporation.adliraihan.gmaker

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import later.corporation.adliraihan.gmaker.firebase.Database
import later.corporation.adliraihan.gmaker.firebase.FirebaseLandingActivity
import later.corporation.adliraihan.gmaker.firebase.FirebaseLoginActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrower)
        var CurrentUsernameLogon = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        var getPref = PreferenceManager.getDefaultSharedPreferences(this)
        var varNote = getPref.getString("autostartNotification",1.toString())
        var mXiaomi:String? = "Xiaomi"
        Log.i("XIAOMI V:" , android.os.Build.MANUFACTURER)
        if(mXiaomi.equals(android.os.Build.MANUFACTURER) && varNote.equals(0.toString())){
            Log.i("XIAOMI MANUFACTURER :" , "SUCCESS")
            var intentComponent = Intent()
            Toast.makeText(this,"Set Autostart pada aplikasi GMaker.",Toast.LENGTH_SHORT).show()
            intentComponent.setComponent(ComponentName("com.miui.securitycenter","com.miui.permcenter.autostart.AutoStartManagementActivity"))
            startActivity(intentComponent)
        }

        if(CurrentUsernameLogon != null){
            var InterGlobal = Intent(this, FirebaseLandingActivity::class.java)
            Handler().postDelayed({
                startActivity(InterGlobal)
                this.finish()
            },2000)
        }else{
            var InterGlobal = Intent(this, FirebaseLoginActivity::class.java)
            Handler().postDelayed({
                startActivity(InterGlobal)
                this.finish()
            },2000)
        }

        /*Thrower ke First Run APP [Update Later]

        val getSharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        var CurrentUsernameLogon = LoginActivity().getSharedPreferenceforWorld(this)

        println("Current LOGIN : " + CurrentUsernameLogon)
        if(CurrentUsernameLogon != null){
            var InterGlobal = Intent(this, LandingActivity::class.java)
            Handler().postDelayed({
                startActivity(InterGlobal)
                this.finish()
            },2000)
        }else{
            var InterGlobal = Intent(this, FirstRunActivity::class.java)
            Handler().postDelayed({
                startActivity(InterGlobal)
                this.finish()
            },2000)
        }



        var cdate = calendar.currentDate.toInt()
        var cmon = calendar.currentMonth.toInt()
        var cyear = calendar.currentYear.toInt()

        var tdate = calendar.targetDate.toInt()
        var tmon = calendar.targetMonth.toInt()
        var tyear = calendar.targetYear.toInt()
        var b = 0;var a =0
        if(cyear != tyear){

             a = cdate + (cmon*30)
             b = tdate + (tmon*30) + ((tyear-cyear)*365)
            println(a-b)
        }else{

        }
        Log.i("TOTAL", b.toString())
        */
    }

    fun setNotificationAutoStart(){
        val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this )
        val editor = getSharedPref.edit()
        editor.putString("autostartNotification", 0.toString())
        editor.commit()
    }


}
