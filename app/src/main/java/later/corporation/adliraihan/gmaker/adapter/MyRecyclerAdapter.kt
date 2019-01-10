package later.corporation.adliraihan.gmaker.adapter

import android.app.ActionBar
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
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.child_seemore_detailpeek.view.*
import later.corporation.adliraihan.gmaker.R
import later.corporation.adliraihan.gmaker.firebase.FirebaseCalendar
import later.corporation.adliraihan.gmaker.firebase.FirebaseDetailActivity
import later.corporation.adliraihan.gmaker.firebase.FirebaseLandingActivity
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class MyRecyclerAdapter(
        private val agendaJudul:ArrayList<String>,
        private val agendaType:ArrayList<String>,
        private val agendaDate:ArrayList<String>,
        private val agendaDesk:ArrayList<String>,
                        val contentData:Context ) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dude = itemView.findViewById<TextView>(R.id.text_Recycler_1)
        val dude2 = itemView.findViewById<TextView>(R.id.descpeek)
        val dude4 = itemView.findViewById<ImageView>(R.id.categorycolor)
        val dude5 =  itemView.findViewById<TextView>(R.id.fulldatemonth)
        val dude6 =  itemView.findViewById<TextView>(R.id.integerDate)
        val dayLeft = itemView.findViewById<TextView>(R.id.days_left)
        val GodFather = itemView.findViewById<LinearLayout>(R.id.taskListButton)
        val seemBtn = itemView.findViewById<ImageView>(R.id.see_more_btn)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyRecyclerAdapter.ViewHolder {
        val myHolder = LayoutInflater.from(p0.context).inflate(R.layout.activity_landing_recycler_child_holder_update,p0,false)
        return ViewHolder(myHolder)
    }

    override fun getItemCount(): Int {
        return agendaJudul.size
    }

    override fun onBindViewHolder(p0: MyRecyclerAdapter.ViewHolder, p1: Int) {
        var expiredC = ContextCompat.getColor(contentData,R.color.BColorDanger)
        var goingC = ContextCompat.getColor(contentData,R.color.BColorSuccess)
        var plString = agendaDate[p1]
        val HasilCompare = FirebaseCalendar().CompareTwo("",plString)
        var DateInteger = plString[0].toString() + plString[1].toString()
        var MonthInteger = plString[3].toString() + plString[4].toString() + plString[5].toString()
        val fulldate = FirebaseCalendar().stringIntstr(MonthInteger)


        p0.apply{
            val Extansi =
                    LayoutInflater.from(contentData).inflate(R.layout.child_seemore_detailpeek,GodFather,false).apply {
                        HolderDetailPeek.startAnimation((AnimationUtils.loadAnimation(contentData,R.anim.abc_slide_in_top)))
                        deleteagendaparent_peek.setOnClickListener {
                            Toast.makeText(contentData,"Are you Sure ?",Toast.LENGTH_SHORT).show()
                        }
                        closebtn_peek.setColorFilter(expiredC)
                        closebtn_peek.setBackgroundColor(expiredC)
                        datedetails_peek.setText(fulldate)
                        descdetails_peek.setText(agendaDesk[p1])
                    }
            dude.setText(agendaJudul[p1])
            dude2.setText("${agendaDesk[p1]}")
            dude5.setText(fulldate)
            dude6.setText(DateInteger)
            HasilCompare?.toInt().let {
                if(it == 0){
                    goingC.also {
                        dayLeft.setTextColor(it)
                        dude4.setBackgroundColor(it)
                    }
                    Extansi.apply {
                        dleftdetails_peek.setText("Sedang berlangsung !")
                        deleteagendaparent_peek.apply {
                            setText("Tandai Sudah Berakhir")
                            setTextColor(ContextCompat.getColor(contentData,R.color.colorAccent_2))
                            setBackgroundColor(expiredC)
                        }
                    }
                }else if(it!! < 0){
                    expiredC.also {
                        dude4.setBackgroundColor(it)
                        dayLeft.setTextColor(it)
                    }
                    dayLeft.setText("Expired !")
                    Extansi.dleftdetails_peek.setText("Sudah berakhir ${it*-1} Hari yang lalu!")
                    Extansi.deleteagendaparent_peek.apply {
                        setText("Hapus Agenda Expired")
                        setTextColor(ContextCompat.getColor(contentData,R.color.colorAccent_2))
                        setBackgroundColor(expiredC)
                    }
                }else{
                    dayLeft.setText(it.toString() + " Days Left !")
                    Extansi.dleftdetails_peek.setText("${it} Hari lagi")
                }

                seemBtn.setOnLongClickListener {
                    GodFather.apply {
                        addView(Extansi)
                        seemBtn.isEnabled = false
                    }
                    return@setOnLongClickListener true
                }
                seemBtn.setOnClickListener{
                    contentData.startActivity(
                            Intent(contentData,FirebaseDetailActivity::class.java).apply {
                                putExtra("varfirst",agendaJudul[p1])
                                putExtra("varsecond",agendaDesk[p1])
                                putExtra("varthird",agendaDate[p1])
                                putExtra("varforth",agendaType[p1])
                                putExtra("varleft", HasilCompare?.toInt())
                            }
                    )
                }
            }
        }

    }
}

