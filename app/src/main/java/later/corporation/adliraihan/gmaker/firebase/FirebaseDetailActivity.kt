package later.corporation.adliraihan.gmaker.firebase

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details_manage.*
import later.corporation.adliraihan.gmaker.R

class FirebaseDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getBundles()
        backdetails.setOnClickListener{finish()}
       deleteagendaparent.setOnLongClickListener{
            var phoneDisplay = windowManager.defaultDisplay
            var dl = Dialog(this)
            dl.setContentView(R.layout.activity_details_manage)
            dl.thisDialogAgendaDetails.layoutParams.width = phoneDisplay.width
            dl.show()
            return@setOnLongClickListener  true
        }
        deleteagendaparent.setOnClickListener{
            deleteAgenda()
        }
    }
    fun deleteAgenda(){
        var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        var removeDb = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_agenda/" +
                "${intent.extras.getString("varfirst")}")
        removeDb
                .removeValue()
        finish()
        startActivity(Intent(this,FirebaseLandingActivity::class.java))
    }
    fun getBundles(){
        var expiredC = ContextCompat.getColor(this,R.color.BColorDanger)
        var goingC = ContextCompat.getColor(this,R.color.BColorSuccess)
        var budlesGet = intent.extras
        var one = intent.getStringExtra("varfirst")
        var two = budlesGet.getString("varsecond","")
        var three = budlesGet.getString("varthird","")
        var four = budlesGet.getString("varforth","")
        var left = budlesGet.getInt("varleft",0)
        try{
            if(left.toInt() < 0) {
                left *= -1
                var leftstr = left.toString()
                leftstr.replace("-","")
                dleftdetails.setText("This agenda is expired " +  leftstr + " days ago")
            }else if(left.toInt() == 0) {
                dleftdetails.setText("This agenda is on going !")
            }else{
                dleftdetails.setText(left.toString() + "Days Left")
            }
        }
        catch (E:Exception){
            println(E)
        }
        titledetails.setText(one)
        datedetails.setText("Date $three")
        descdetails.setText(two)
    }
}