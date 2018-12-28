package later.corporation.adliraihan.gmaker.firebase

import android.app.Dialog
import android.content.Context
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.core.view.View
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_firebase_create.*
import kotlinx.android.synthetic.main.dialog_calendar.*
import later.corporation.adliraihan.gmaker.R

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
        }
        StartAgenda.setOnClickListener{
            calendarDialog(this)
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
    fun calendarDialog(konteks:Context){
        var DialogCalendar = Dialog(konteks)
        DialogCalendar.setContentView(R.layout.dialog_calendar)
        DialogCalendar.show()
        DialogCalendar.calendarViewCreate.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var monthi = FirebaseCalendar.cal.m[month]
            var domMod = ""
            if(dayOfMonth.toString().length < 2)
                domMod = "0" + dayOfMonth.toString()
            else
                domMod = dayOfMonth.toString()

            StartAgenda.setText( domMod + "/" + monthi.toString() + "/" + year.toString())
        }
       DialogCalendar.dateconfrim_btn.setOnClickListener{
           DialogCalendar.hide()
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
        }
        catch (E:Exception){
            Toast.makeText(this,"Unable to store agenda" , Toast.LENGTH_SHORT).show()
            println(E.toString())
        }
    }
}