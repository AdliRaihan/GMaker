package later.corporation.adliraihan.gmaker

import android.content.*
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import later.corporation.adliraihan.gmaker.firebase.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var receiver:BroadcastReceiver = FirebaseReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrower)
        var CurrentUsernameLogon = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        var getPref = PreferenceManager.getDefaultSharedPreferences(this)
        var varNote = getPref.getString("autostartNotification",1.toString())
        //=============//
        // Xiaomi Step  //
        //=============//
        var mXiaomi:String? = "Xiaomi"
        Log.i("XIAOMI V:" , android.os.Build.MANUFACTURER)
        if(mXiaomi.equals(android.os.Build.MANUFACTURER) && varNote.equals(0.toString())){
            var intentComponent = Intent()
            Toast.makeText(this,"Set Autostart pada aplikasi GMaker.",Toast.LENGTH_SHORT).show()
            intentComponent.setComponent(ComponentName("com.miui.securitycenter","com.miui.permcenter.autostart.AutoStartManagementActivity"))
            startActivity(intentComponent)
        }
        var intf:IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        }
        registerReceiver(receiver,intf)

        if(CurrentUsernameLogon != null){
            var InterGlobal = Intent(this, FirebaseLandingActivity::class.java)
            Handler().postDelayed({
                startService(Intent(this, RSSPullService::class.java))
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
    }

    fun setNotificationAutoStart(){
        val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this )
        val editor = getSharedPref.edit()
        editor.putString("autostartNotification", 0.toString())
        editor.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


}
