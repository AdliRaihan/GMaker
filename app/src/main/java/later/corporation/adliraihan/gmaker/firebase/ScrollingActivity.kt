package later.corporation.adliraihan.gmaker.firebase

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import kotlinx.android.synthetic.main.activity_scrolling.*
import later.corporation.adliraihan.gmaker.R
import org.w3c.dom.Text
import java.lang.StringBuilder

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        //
        println("TOOLBAR TITLER ${toolbar_layout.title}")
        var sb:SpannableString = SpannableString("dordor").apply {
            setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        //
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
