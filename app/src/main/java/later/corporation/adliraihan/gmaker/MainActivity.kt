package later.corporation.adliraihan.gmaker

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import later.corporation.adliraihan.gmaker.firebase.Database
import later.corporation.adliraihan.gmaker.firebase.FirebaseLandingActivity
import later.corporation.adliraihan.gmaker.firebase.FirebaseLoginActivity
import java.util.*

class MainActivity : AppCompatActivity() {


    object calendar{

        var current = "11-12-2018"
        var target = "11-02-2019"

        var currentDate = current[0].toString() + current[1].toString()
        var targetDate = current[0].toString() + current[1].toString()

        var currentMonth = current[3].toString() + current[4].toString()
        var targetMonth = target[3].toString() + target[4].toString()


        var currentYear = current[6].toString() + current[7].toString() + current[8].toString() + current[9].toString()
        var targetYear = target[6].toString() + target[7].toString() + target[8].toString()+ target[9].toString()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        var Tags = "MainActivity"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrower)

        // ALGORITHM DATA //
        var CurrentUsernameLogon = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
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
}
