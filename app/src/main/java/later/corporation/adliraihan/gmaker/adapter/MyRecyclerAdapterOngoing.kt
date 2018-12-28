package later.corporation.adliraihan.gmaker.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import later.corporation.adliraihan.gmaker.R
import later.corporation.adliraihan.gmaker.firebase.FirebaseCalendar
import later.corporation.adliraihan.gmaker.firebase.FirebaseDetailActivity
import later.corporation.adliraihan.gmaker.firebase.FirebaseLandingActivity
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class MyRecyclerAdapterOngoing(private val agendaJudul:ArrayList<String>,
                               private val agendaType:ArrayList<String>,
                               private val agendaTime:ArrayList<String>,
                               private val agendaDate:ArrayList<String>,
                               private val agendaDesk:ArrayList<String>,
                               val contentData:Context ) : RecyclerView.Adapter<MyRecyclerAdapterOngoing.ViewHolder>() {
    var ongoing_data = 0
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dude = itemView.findViewById<TextView>(R.id.titleongoing)
        var dude2 = itemView.findViewById<TextView>(R.id.dateongoing)
        var dude4 = itemView.findViewById<TextView>(R.id.descongoing)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyRecyclerAdapterOngoing.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.recycler_child_ongoing,p0,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return agendaJudul.size
    }

    override fun onBindViewHolder(p0: MyRecyclerAdapterOngoing.ViewHolder, p1: Int) {
        var expiredC = ContextCompat.getColor(contentData,R.color.BColorDanger)
        var goingC = ContextCompat.getColor(contentData,R.color.BColorSuccess)
        var plString = agendaDate[p1]
        println("AGENDA DATE:" + agendaDate[p1].toString())
        var let = FirebaseCalendar().CompareTwo("",plString)
        println("SISA : " + let)
        var dleft = let.toString().toInt()
        p0.dude.setText(agendaJudul[p1])
        p0.dude2.setText(agendaDate[p1])
        p0.dude4.setText(agendaDesk[p1])
    }
}

