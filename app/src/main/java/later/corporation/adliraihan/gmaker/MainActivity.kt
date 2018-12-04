package later.corporation.adliraihan.gmaker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var Tags = "MainActivity"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrower)

        //Thrower ke First Run APP
        Thread.sleep(2000)
        var InterGlobal = Intent(this, FirstRunActivity::class.java)
        startActivity(InterGlobal)
    }
}
