package later.corporation.adliraihan.gmaker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        daftar_txt_btn.setOnClickListener(){
            var InterGlobal = Intent(this, DaftarActivity::class.java)
            startActivity(InterGlobal)
        }
    }
}