package later.corporation.adliraihan.gmaker

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var Tags = "MainActivity"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrower)


        //Thrower ke First Run APP [Update Later]

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
    }
}
