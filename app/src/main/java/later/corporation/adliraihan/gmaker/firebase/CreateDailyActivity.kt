package later.corporation.adliraihan.gmaker.firebase

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.text.format.Time
import android.widget.LinearLayout
import android.widget.TimePicker
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.view.View
import kotlinx.android.synthetic.main.activity_create_daily.*
import kotlinx.android.synthetic.main.activity_details_manage.*
import later.corporation.adliraihan.gmaker.R
import java.text.SimpleDateFormat
import java.util.*

class CreateDailyActivity : AppCompatActivity() {
    var first_A:String = "PM"
    var sec_A:String = "PM"
    private var scheduleIndex = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_daily)

        firstHH.requestFocus()
        currentYearPlusOne()
        initBtn()
        initSchedule()
        initLogicIndex()
        initLogicFinish()
        textListener()
        initLogicInsert()
    }
    fun initLogicFinish(){
        if(firstHH.text.isEmpty() || firstMM.text.isEmpty() || secHH.text.isEmpty() || secMM.text.isEmpty()){
            finishschedule.visibility = android.view.View.GONE
        }else{
            finishschedule.visibility = android.view.View.VISIBLE
        }
    }
    fun textListener(){
        firstHH.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(firstHH.length() == 2) {
                    if (firstHH.text.toString().toInt() > 12) {
                        firstHH.setText("12")
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(firstHH.length() == 2){
                    firstMM.setText("")
                    firstMM.requestFocus()
                }
            }
        })

        firstMM.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
             if(firstMM.length() == 2){
                 if(firstMM.text.toString().toInt() == 60){
                     var cfText = firstHH.text.toString().toInt()
                     if(cfText < 10){
                         firstHH.setText("0" + (cfText + 1).toString())
                     }else{
                         firstHH.setText((cfText + 1).toString())
                     }
                     firstMM.setText("00")
                 }
                 secHH.setText("")
                 secHH.requestFocus()
             }
            }
        })
        secMM.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(secMM.length() == 2){
                    initLogicFinish()
                    if(secMM.text.toString().toInt() == 60) {
                        if(secMM.text.toString().toInt() == 60){
                        var cfText = secHH.text.toString().toInt()
                        if(cfText < 10){
                            secHH.setText("0" + (cfText + 1).toString())
                        }else{
                            secHH.setText((cfText + 1).toString())
                        }
                            secMM.setText("00")
                        }
                    }
                }
            }
        })
        secHH.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(secHH.length() == 2){
                    if(secHH.text.toString().toInt() > 12){
                        secHH.setText("12")
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(secHH.length() == 2){
                    resetBtn.visibility = android.view.View.VISIBLE
                    secMM.setText("")
                    secMM.requestFocus()
                }else{
                    resetBtn.visibility = android.view.View.GONE
                }
            }
        })
    }

    fun initBtn(){

        var colorBPrimary = ContextCompat.getColor(this,R.color.BColorPrimary)
        var colorBDark = ContextCompat.getColor(this,R.color.colorPrimaryDark_Opacity2)
        amschedule_11.setOnClickListener{
            amschedule_11.setTextColor(colorBPrimary)
            pmschedule_11.setTextColor(colorBDark)
            first_A = "AM"
        }
        pmschedule_11.setOnClickListener{
            pmschedule_11.setTextColor(colorBPrimary)
            amschedule_11.setTextColor(colorBDark)
            first_A = "PM"
        }
        amschedule_12.setOnClickListener{
            amschedule_12.setTextColor(colorBPrimary)
            pmschedule_12.setTextColor(colorBDark)
            sec_A = "AM"
        }
        pmschedule_12.setOnClickListener{
            pmschedule_12.setTextColor(colorBPrimary)
            amschedule_12.setTextColor(colorBDark)
            sec_A = "PM"
        }

        resetBtn.setOnClickListener {
            clear()
        }
    }

    fun initSchedule(){
        finishschedule.setOnClickListener {
            if(firstHH.text.isEmpty() || firstMM.text.isEmpty() || secHH.text.isEmpty() || secMM.text.isEmpty()) {
                Toast.makeText(this, "Isi seluruh form !", Toast.LENGTH_SHORT).show()
            }else{
                var myMsg = CreateDailyFunctionTimer().main(
                        firstHH.text.toString(),
                        firstMM.text.toString(),
                        first_A,
                        secHH.text.toString(),
                        secMM.text.toString(),
                        sec_A
                )
                if (!myMsg == true) {
                    firstHH.requestFocus()
                    clear()
                    finishschedule.visibility = android.view.View.GONE
                    Toast.makeText(this, "Failed input !", Toast.LENGTH_SHORT).show()
                } else {
                    var finalFirstTime = firstHH.text.toString() + ":" + firstMM.text.toString() + " $first_A"
                    var finalSecTime = secHH.text.toString() + ":" + secMM.text.toString() + " $sec_A"
                    println(finalFirstTime + "\n" + finalSecTime)
                }
            }
        }
    }
    fun clear(){
        firstHH.text.clear()
        firstMM.text.clear()
        secHH.text.clear()
        secMM.text.clear()
        firstHH.requestFocus()
    }

    fun initLogicIndex(){
        var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        var prefRead = Database.variables.myRead.child("user_" + setUser + "/account_schedule/" )
        var prefFunction = prefRead.orderByChild("index")
        prefFunction.addValueEventListener(object  : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.child("index").exists()){
                    scheduleIndex = p0.child("index").value.toString()
                }
                println("This Id Index: $scheduleIndex")
            }
        })
    }

    fun currentYearPlusOne() : String{
        var cTimedate = Calendar.getInstance()
        cTimedate.add(Calendar.DAY_OF_YEAR,+1)
        var tTD = cTimedate.time
        var dTD = tTD.date
        var mTD = tTD.month
        var dateFormat = SimpleDateFormat("yyyy")
        var currentYear = dateFormat.format(tTD)
        var mTDS = FirebaseCalendar().CalendarFullString(mTD+1)
        var FTDS = "$dTD $mTDS $currentYear"
        currentDateDaily.setText("Schedule For : $FTDS")
        return FTDS
    }

    fun initLogicInsert(){
        finishschedule.setOnClickListener {
            if(scheduleIndex.toInt() > 3){
                Toast.makeText(this,"Your max Schedule is 3 ! buy here for more",Toast.LENGTH_SHORT).show()
            }else{
                var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
                var scInt = scheduleIndex.toInt() + 1
                scheduleIndex = scInt.toString()
                var sIndex =  Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/index")
                sIndex.setValue(scheduleIndex)
                var sLocation = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/location")
                sLocation.setValue(location.text.toString())
                var sMeetWith = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/meetwith")
                sMeetWith.setValue(meetwith.text.toString())
                var stask = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/task")
                stask.setValue(task.text.toString())
                // CALENDAR //
                var sTimeDate = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/timedate")
                sTimeDate.setValue(currentYearPlusOne())
                // END OF CALENDAR //
                var sTimeStart = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/timestart")
                sTimeStart.setValue(firstHH.text.toString() + ":" + firstMM.text.toString() + " $first_A")
                var sTimeEnd = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/timeend")
                sTimeEnd.setValue(secHH.text.toString() + ":" + secMM.text.toString()  + " $sec_A")
                var sTodo = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex/todo")
                sTodo.setValue(todo.text.toString())
                println(sTodo.toString())
                finish()
            }
        }

        createsc_back.setOnClickListener {
            this.finish()
            startActivity(Intent(this,FirebaseLandingActivity::class.java))
//            var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
//            var removeDb = Database.variables.dbConnection.getReference( "user_$setUser" + "/account_schedule/schedule_$scheduleIndex")
//            removeDb
//                    .removeValue()
        }

    }
}

