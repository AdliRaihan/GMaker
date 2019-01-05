package later.corporation.adliraihan.gmaker.firebase

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.login_failed.*
import later.corporation.adliraihan.gmaker.R
import java.lang.Exception

class FirebaseDaftarActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        submit_asDaftar.setOnClickListener{
            validationRegister()
        }
        backgraybtn.setOnClickListener {
            finish()
        }
    }

    var afterLoginReaderGoFinish = false;
    fun validationRegister(){
        // IMPLEMENT LATER
        if(username_daftar.text.isEmpty()){
        }else if(password_daftar.text.isEmpty()){
        }else{
            onRegister()
        }
    }
    fun drawableChanger(Edittext:EditText,fail:Any?,reset:Any?){
        Edittext.background.apply {
            resources.getDrawable(R.drawable.edittext_primary_drwbl_wrong)
            Handler().postDelayed({
                resources.getDrawable(R.drawable.edittext_primary_drwbl)
            },5000)
        }
    }
    fun onRegister(){
        var username = username_daftar.text.toString()
        var password = password_daftar.text.toString()
        var userpassword = password_daftar_confrim.text.toString()
        if(password.equals(userpassword) && password != "" || password != null){
            var checkURI = Database.variables.myRead.path
            println("THIS PATH : " + checkURI)
            var checkRedu = FirebaseDatabase.getInstance().getReference("/")

            var myFunc = checkRedu.orderByChild("user_" + username).addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(afterLoginReaderGoFinish == false){
                        if(p0.child("user_" + username).exists()){
                            var D = Dialog(this@FirebaseDaftarActivity)
                            D.setContentView(R.layout.login_failed)
                            D.logs_error.setText("The username already Exist !")
                            D.infoLogs.setText("Username " + username + " telah terdaftar didalam database kami.")
                            if(D.isShowing) D.hide()
                            D.show()
                            D.tryAgain_btn.setOnClickListener(){
                                username_daftar.setText("")
                                password_daftar.setText("")
                                password_daftar_confrim.setText("")
                                checkRedu.removeEventListener(this)
                                D.hide()
                            }
                        }else{
                            var UserDir = "user_" + username + "/account_information/"
                            var dbConnectionUsername = Database.variables.dbConnection.getReference(UserDir + "username")
                            var dbConnectionPassword = Database.variables.dbConnection.getReference(UserDir + "password")
                            try{
                                dbConnectionUsername.setValue(username)
                                 dbConnectionPassword.setValue(password)
                                checkRedu.removeEventListener(this)
                                Toast.makeText(applicationContext,"Create account successfully",Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            catch (E:Exception){
                                Toast.makeText(applicationContext,"Register failed , contact Developer !",Toast.LENGTH_SHORT).show()
                                println(E.toString())
                                finish()
                            }
                        }
                    }
                }
            })
        }else{
            Toast.makeText(applicationContext,"Confrim password did not match !",Toast.LENGTH_SHORT).show()
        }
        println(username + " " + password);
    }

}