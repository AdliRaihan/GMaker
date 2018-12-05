package later.corporation.adliraihan.gmaker

import android.content.Intent
import android.os.Bundle
import android.sax.StartElementListener
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class DaftarActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        submit_asDaftar.setOnClickListener {
            var InterGlobal = Intent(this,LoginActivity::class.java)
            startActivity(InterGlobal)
        }
    }
}