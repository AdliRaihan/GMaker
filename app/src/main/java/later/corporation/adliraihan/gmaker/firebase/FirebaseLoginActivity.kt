package later.corporation.adliraihan.gmaker.firebase

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Button
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login_failed.*
import later.corporation.adliraihan.gmaker.R

class FirebaseLoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginbtn_intent.setOnClickListener {
            if(!username_Input.text.isEmpty() &&
                !password_Input.text.isEmpty())
            {
                loginbtn_intent.apply {
                    setText(resources.getString(R.string.login_onsubmit_loading))
                    Handler().postDelayed({
                     setText("LOGIN")
                    },5000)
                }
                readLogin(username_Input.text.toString(),password_Input.text.toString(),this)
                // InitializeLogin(password_Input.text.toString(),this)
            }
        }

        daftar_txt_btn.setOnClickListener{
            var Intent = Intent(this,FirebaseDaftarActivity::class.java)
            startActivity(Intent)
        }

        username_Input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validation()
            }
        })
        password_Input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validation()
            }
        })
    }

    // =============
    // READ DATABASE
    // =============
        fun readLogin(username: String?,password: String?,konteks: Context){
            var status_login:Boolean? = null
            var readPref = Database.variables.myRead.child("user_" + username + Database.url.user_account)
            val memberImp = readPref.orderByChild("account_information")
        memberImp.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("Something went Wrong : " + p0.toString())
            }
            override fun onDataChange(p0: DataSnapshot) {
                val userinput = p0.child("password").value.toString()
                println("Username Input :" + username)
                println("Username : " + p0.child("username").value.toString())
                println("Password : " + userinput)
                println("User Input : " + password)
                if(userinput.equals(password)){
                    FsharedPreferenceForWorld()
                }else{
                    auth(username,loginbtn_intent)
                }
            }
        })
    }
    fun auth(username:String?,loginbtn_intent:Button){
        var dialogError = Dialog(this)
            .apply {
                setContentView(R.layout.login_failed)
                logs_error.setText(resources.getString(R.string.login_popup_failed_txt_1 ) + " " + username)
                show()
                tryAgain_btn
                    .setOnClickListener{
                        loginbtn_intent.apply {
                            setText(resources.getString(R.string.login_label))
                        }
                        dismiss()
                    }
            }
    }
    fun validation(){
        var prank = resources.getColor(R.color.colorAccent_2)
        var colorDefault_1  = prank
        if(!username_Input.text.isEmpty() &&
            !password_Input.text.isEmpty())
            loginbtn_intent.setTextColor(resources.getColor(R.color.colorAccent_2))
        else
            loginbtn_intent.setTextColor(resources.getColor(R.color.colorDawn))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    // ================
    // SET PRIVILIAGES FOR ANYBODY WHO
    // ================

    fun FsharedPreferenceForWorld(){
        val getSharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = getSharedPref.edit()
        editor.putString("username",username_Input.text.toString())
        if(editor.commit()){
            this.finish()
            var Intent = Intent(this,FirebaseLandingActivity::class.java)
            startActivity(Intent)
        }
    }
    fun FgetSharedPreferenceforWorld(context:Context):String?{
        var getPref = PreferenceManager.getDefaultSharedPreferences(context)
        return getPref.getString("username",null)
    }
}