package later.corporation.adliraihan.gmaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(){
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val getSharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        var LURD = getSharedPref.getString("username","")
        username_Input.setText(LURD)
        loginbtn_intent.setOnClickListener {
            with(getSharedPref.edit()){
                putString("username",username_Input.text.toString())
                        commit()
            }
            var InterGlobal = Intent(this, LandingActivity::class.java)
            startActivity(InterGlobal)
        }
        daftar_txt_btn.setOnClickListener(){
            var InterGlobal = Intent(this, DaftarActivity::class.java)
            startActivity(InterGlobal)
        }
    }
}