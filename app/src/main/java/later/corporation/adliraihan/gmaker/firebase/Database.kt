package later.corporation.adliraihan.gmaker.firebase

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.database.*
import later.corporation.adliraihan.gmaker.LandingActivity
import later.corporation.adliraihan.gmaker.R
import java.text.SimpleDateFormat
import java.util.*

class Database
{
    object variables{
        val dbConnection = FirebaseDatabase.getInstance()
        val myRead = dbConnection.getReferenceFromUrl("https://gmaker-9c3cb.firebaseio.com/")
    }
    object url{
        val user_agenda = "/account_agenda/"
        val user_account = "/account_information/"
    }
    fun getAvailableSchedulef(userLogged:String,context:Context,mNotification:NotificationManager){
        var Pref = Database.variables.myRead.child("user_$userLogged"  + url.user_agenda )
        var CY = Calendar.getInstance().time
        var CYs = SimpleDateFormat("dd/MMM").format(CY)
        val functionImplements = Pref.orderByChild("title")
        functionImplements.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {
                val children = p0.children
                if(p0.children.count() == 0 ){
                }else{
                    var MapAvailable:Map<Int,String> =mapOf()
                    children.forEach {
                        var datePointer = FirebaseCalendar().CompareTwo("",it.child("date").value.toString())
                        if(datePointer.equals(" -1 ")){
                            var removeDb =  Database.variables.myRead.child("user_$userLogged" + Database.url.user_agenda + "/${it.child("title").value.toString()}" )
                            Log.i("DbStatus","Removed")
                        }else{
                            Log.i("MAP", "$CYs = ${FirebaseCalendar().getDateMonthFromArray((it.child("date").value.toString()))}")
                            if(FirebaseCalendar().getDateMonthFromArray(it.child("date").value.toString()).equals(CYs)){
                                MapAvailable = mapOf(
                                        0 to children.count().toString(),
                                        1 to it.child("title").value.toString(),
                                        2 to it.child("description").value.toString(),
                                        3 to it.child("time").value.toString(),
                                        4 to it.child("date").value.toString(),
                                        5 to it.child("type").value.toString())
                            }
                        }
                    }

                    val Intain = Intent(context, LandingActivity::class.java)
                    var stringTextNote = ""
                    if(MapAvailable.get(0)!!.toInt() == 0)
                        stringTextNote = "Tap to see this agenda."
                    else
                        stringTextNote = "Tap to see ${MapAvailable.get(0)} more agendas."

                    var penIntent = PendingIntent.getActivity(context, 1, Intain,0)
                    var mBuilder = NotificationCompat.Builder(context, 0.toString())
                            .setSmallIcon(R.drawable.notification_template_icon_bg)
                            .setContentTitle("Reminder Agenda : ${MapAvailable.get(1)}")
                            .setContentText(stringTextNote)
                            .setPriority(NotificationCompat.FLAG_ONGOING_EVENT)
                            .setContentIntent(penIntent)
                            .build()
                    mBuilder.flags  = Notification.FLAG_NO_CLEAR
                    mNotification.notify(0,mBuilder)
                }
            }
        })
    }
}