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

class MyRecyclerAdapter(private val agendaJudul:ArrayList<String>,
                        private val agendaType:ArrayList<String>,
                        private val agendaTime:ArrayList<String>,
                        private val agendaDate:ArrayList<String>,
                        private val agendaDesk:ArrayList<String>,
                        val contentData:Context ) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    var ongoing_data = 0
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var dude = itemView.findViewById<TextView>(R.id.text_Recycler_1)
       var dude2 = itemView.findViewById<TextView>(R.id.descpeek)
       var dude4 = itemView.findViewById<ImageView>(R.id.categorycolor)
        var dude5 =  itemView.findViewById<TextView>(R.id.fulldatemonth)
        var dude6 =  itemView.findViewById<TextView>(R.id.integerDate)
        var dayLeft = itemView.findViewById<TextView>(R.id.days_left)
        var GodFather = itemView.findViewById<LinearLayout>(R.id.taskListButton)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyRecyclerAdapter.ViewHolder {

        val v = LayoutInflater.from(p0.context).inflate(R.layout.activity_landing_recycler_child_holder_update,p0,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return agendaJudul.size
    }

    override fun onBindViewHolder(p0: MyRecyclerAdapter.ViewHolder, p1: Int) {
        val opt = RequestOptions().centerCrop()
        //val errorBG:Drawable? = contentData.resources.getDrawable(R.drawable.foto_1)!!
        p0.dude.setText(agendaJudul[p1])
        // p0.dude2.setText(agendaDate[p1])
        //p0.dude4.setText(agendaType[p1])

        var expiredC = ContextCompat.getColor(contentData,R.color.BColorDanger)
        var goingC = ContextCompat.getColor(contentData,R.color.BColorSuccess)
        var plString = agendaDate[p1]
        var let = FirebaseCalendar().CompareTwo("",plString)
        var p = Calendar.getInstance().time
        var dlm = p.month
        var dl = p.date;var TimeLeft:Int = 0
        var DateInteger = plString[0].toString() + plString[1].toString()
        var MonthInteger = plString[3].toString() + plString[4].toString() + plString[5].toString()
        val fulldate = FirebaseCalendar().stringIntstr(MonthInteger)

        p0.dude6.setText(DateInteger)
        p0.dude5.setText(fulldate)
        p0.dude2.setText(agendaDesk[p1])
        var dleft = let.toString().toInt()
        if(dleft == 0) {
            goingC.also {
                p0.dude4.setBackgroundColor(it)
                p0.dayLeft.setTextColor(it)
            }
            p0.dayLeft.setText("On Going")
        }else if(dleft < 0) {
            expiredC.also {
                p0.dude4.setBackgroundColor(it)
                p0.dayLeft.setTextColor(it)
            }
            p0.dayLeft.setText("Expired !")
        }else
            p0.dayLeft.setText(let.toString() + " Days Left !")

        p0.GodFather.setOnClickListener(){
            val GlobeIntent  = Intent(contentData,FirebaseDetailActivity::class.java)
            GlobeIntent.putExtra("varfirst",agendaJudul[p1])
                    .putExtra("varsecond",agendaDesk[p1])
                    .putExtra("varthird",agendaDate[p1])
                    .putExtra("varforth",agendaType[p1])
                    .putExtra("varleft",dleft)

            contentData.startActivity(GlobeIntent)
        }



    }
}

