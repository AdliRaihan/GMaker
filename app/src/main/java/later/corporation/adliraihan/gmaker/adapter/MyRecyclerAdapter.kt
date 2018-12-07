package later.corporation.adliraihan.gmaker.adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import later.corporation.adliraihan.gmaker.R

class MyRecyclerAdapter(private val agendaId:ArrayList<String>,private val judul_agenda:ArrayList<String>,private val info_agenda:ArrayList<String>,private val info_imgLoc:ArrayList<String>,val contentData:Context ) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    var pos_2 = 0
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dude = itemView.findViewById<TextView>(R.id.text_Recycler_1)
        var dude2 = itemView.findViewById<TextView>(R.id.text_Recycler_2)
        var dude3 = itemView.findViewById<ImageView>(R.id.imgHolderme)
        var dude4 = itemView.findViewById<TextView>(R.id.text_Recycler_3)
        var GodFather = itemView.findViewById<LinearLayout>(R.id.taskListButton)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.activity_landing_recycler_child_holder,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return judul_agenda.size
    }

    override fun onBindViewHolder(p0: MyRecyclerAdapter.ViewHolder, p1: Int) {
        val opt = RequestOptions().centerCrop()
        p0.dude.setText(judul_agenda[p1])
        p0.dude2.setText(info_agenda[p1])
        Glide
                .with(contentData)
                .asBitmap()
                .load(info_imgLoc[p1])
                .apply(opt)
                .into(p0.dude3)
        p0.dude4.setText(agendaId[p1])
        p0.GodFather.setOnClickListener(){
            val GlobeIntent  = Intent(contentData,DetailsActivity::class.java)
                    .putExtra("agenda_id",p0.dude4.text)
                    .putExtra("agenda_img",info_imgLoc[p1])
            contentData.startActivity(GlobeIntent)
        }
    }


}