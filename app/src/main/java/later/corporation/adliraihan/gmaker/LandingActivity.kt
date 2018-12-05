package later.corporation.adliraihan.gmaker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_landing_recycler_parent.*
import later.corporation.adliraihan.gmaker.adapter.MyRecyclerAdapter

class LandingActivity : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_recycler_parent)

        var ArraySuperNama = resources.getStringArray(R.array.taskList)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyRecyclerAdapter(ArraySuperNama)
        recyclerView = findViewById<RecyclerView>(R.id.RecyclerMe).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        ScrollBarLandingUserLayout.smoothScrollTo(0,0)
    }
/*
    override fun isActivityTransitionRunning(): Boolean {
        var ArraySuperNama = resources.getStringArray(R.array.taskList)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyRecyclerAdapter(ArraySuperNama)
        recyclerView = findViewById<RecyclerView>(R.id.RecyclerMe).apply {

            layoutManager = viewManager
            adapter = viewAdapter
        }
        return super.isActivityTransitionRunning()
    }
    */
}