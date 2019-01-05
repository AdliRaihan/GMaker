package later.corporation.adliraihan.gmaker.firebase

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.*
import com.google.firebase.database.core.view.View
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_firebase_create.*
import kotlinx.android.synthetic.main.dialog_calendar.*
import later.corporation.adliraihan.gmaker.R
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class FirebaseCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_create)
        var adapterS = ArrayAdapter.createFromResource(this,R.array.type_agenda,R.layout.support_simple_spinner_dropdown_item)
        adapterS.setDropDownViewResource(R.layout.spinner_adapter)
        typeDataAgenda.adapter = adapterS
        var setUser  = FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        btnAgenda.setOnClickListener {
            if (titleDataAgenda.text.toString() != "" &&
                    DescDataAgenda.text.toString() != ""  &&
                    StartAgenda.text.toString() != ""){
                insertAgenda()
            }
        }
        creategenda_back.setOnClickListener{
            finish()
            startActivity(Intent(this,FirebaseLandingActivity::class.java))
        }
        StartAgenda.setOnClickListener{
            calendarDialog(this,StartAgenda)
        }
        DescDataAgenda.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var maxL = 250
                var maxC = DescDataAgenda.text.length
                var maxA = maxL - maxC

                if(maxA == 1 || maxA == 0)
                    charleft_createAgenda.setText(maxA.toString() + " Character left.")
                else
                    charleft_createAgenda.setText(maxA.toString() + " Characters left.")
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish()
            startActivity(Intent(this,FirebaseLandingActivity::class.java))
        }
        return super.onKeyDown(keyCode, event)
    }
    fun calendarDialog(konteks:Context,editChange:Button){
        var monthi:String?
        var domMod:String?
        Dialog(konteks).apply{
            setContentView(R.layout.dialog_calendar)
            calendarViewCreate.minDate = Calendar.getInstance().timeInMillis
            show()
            calendarViewCreate.setOnDateChangeListener { view, year, month, dayOfMonth ->
                monthi = FirebaseCalendar.cal.m[month]
                domMod = dayOfMonth.toString()
                if(dayOfMonth.toString().length < 2)   domMod = "0" + dayOfMonth.toString()
                // Suntik Text //
                editChange.setText(
                        StringBuilder().also {
                            it.append("$domMod/")
                                    .append("$monthi/")
                                    .append("$year")
                        }
                )
                dismiss()
                if(isShowing) hide()
            }
            dateconfrim_btn.setOnClickListener{
                hide()
            }
        }
    }
    fun insertAgenda(){
        var pathUsername = "user_" + FirebaseLoginActivity().FgetSharedPreferenceforWorld(this)
        var agendaTitle = Database.variables.dbConnection.getReference( pathUsername + Database.url.user_agenda +
                titleDataAgenda.text.toString() +  "/title")
        var agendaDesc = Database.variables.dbConnection.getReference( pathUsername + Database.url.user_agenda +
                titleDataAgenda.text.toString() +  "/description")
        var agendaTarget = Database.variables.dbConnection.getReference( pathUsername + Database.url.user_agenda +
                titleDataAgenda.text.toString() +  "/date")
        var agendaType = Database.variables.dbConnection.getReference( pathUsername + Database.url.user_agenda +
                titleDataAgenda.text.toString() +  "/type")
        try{
            agendaTitle.setValue(titleDataAgenda.text.toString())
            agendaDesc.setValue(DescDataAgenda.text.toString())
            agendaTarget.setValue(StartAgenda.text.toString())
            agendaType.setValue(typeDataAgenda.selectedItem.toString())
            Toast.makeText(this,"Agenda stored successfully" , Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this,FirebaseLandingActivity::class.java))
        }
        catch (E:Exception){
            Toast.makeText(this,"Unable to store agenda" , Toast.LENGTH_SHORT).show()
            println(E.toString())
        }
    }
}