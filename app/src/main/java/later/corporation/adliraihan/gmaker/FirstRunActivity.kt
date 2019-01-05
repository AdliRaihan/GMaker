package later.corporation.adliraihan.gmaker

import android.annotation.SuppressLint
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.text.Layout
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_thrower.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class FirstRunActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Login Activity
        var PopUp_Dev = resources.getLayout(R.layout.popup_thrower)
        buttonActivity_Login.setOnClickListener() {
            setContentView(R.layout.popup_thrower)
            developer_btn.setOnClickListener() {
                var InterGlobal = Intent(this, LandingActivity::class.java)
                startActivity(InterGlobal)
            }
            developer_btn_2.setOnClickListener() {
                var InterGlobal = Intent(this, LoginActivity::class.java)
                startActivity(InterGlobal)
            }
        }
    }
}