package later.corporation.adliraihan.gmaker.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import later.corporation.adliraihan.gmaker.R

class MyRecyclerAdapter(private val item:Array<String>) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    var pos_2 = 0
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dude = itemView.findViewById<TextView>(R.id.text_Recycler_1)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.activity_landing_recycler_child_holder,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(p0: MyRecyclerAdapter.ViewHolder, p1: Int) {
            p0.dude.setText(item[p1])
    }


}