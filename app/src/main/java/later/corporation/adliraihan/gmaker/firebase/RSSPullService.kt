package later.corporation.adliraihan.gmaker.firebase

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import later.corporation.adliraihan.gmaker.R
import java.util.*
import kotlin.collections.ArrayList

class RSSPullService :  IntentService(RSSPullService::class.simpleName){
    object dataVar{
        var titleAvailable:ArrayList<String> = ArrayList()
    }


    override fun onHandleIntent(intent: Intent?) {
        onStartCommand(intent,0,1)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        android.os.Handler().postDelayed({
            var managerNot = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            getAvailableSchedule(FirebaseLoginActivity().FgetSharedPreferenceforWorld(applicationContext).toString(),applicationContext,managerNot)
            getAvailableSchedule()
            ReminderOnSchedule(managerNot)
            Log.i("SERVICES" , "is Running !")
            startService(intent)
        },10000)
        return Service.START_STICKY
    }
    fun ReminderOnSchedule(mNot:NotificationManager){
        var stacks = TaskStackBuilder.create(this)
        val CreateDailyInt = Intent(applicationContext, CreateDailyActivity::class.java)
        stacks.addParentStack(CreateDailyActivity().javaClass)
        stacks.addNextIntent(CreateDailyInt)
        var penIntent = stacks.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        var largeBitmap = BitmapFactory.decodeResource(resources,R.drawable.notification_template_icon_bg)
        if(FirebaseCalendar().getHours().equals("10") &&
                FirebaseCalendar().getLocale().equals("PM") &&
                FirebaseCalendar().getMinute().toInt() < 30){
            var mBuilder = NotificationCompat.Builder(applicationContext, 0.toString())
                    .setSmallIcon(R.drawable.notification_template_icon_bg)
                    .setContentTitle("Schedule Daily Reminder")
                    .setContentText("Apakah anda telah mengisi schedule untuk besok ?")
                    .setPriority(NotificationCompat.FLAG_ONGOING_EVENT)
                    .setContentIntent(penIntent)
                    .setLargeIcon(largeBitmap)
                    .build()
            mBuilder.flags  = Notification.FLAG_AUTO_CANCEL
           // mNot.notify(0,mBuilder)
        }
    }
    fun AutoDeleteSystem(){
        TODO("NEXT UPDATE INI HARUS SELESAI - Adli Raihan")
    }
    fun ReadingSchedule(){
        TODO("NEXT -> NEXT -> Update (Haruselesaiugha)")
    }
    fun getAvailableSchedule(){
        var Iteration = 0
        if(!dataVar.titleAvailable.isEmpty()){
            var managerNot = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var largeBitmap = BitmapFactory.decodeResource(resources,R.drawable.notification_template_icon_bg)
            var notificationSchedule = NotificationCompat.Builder(applicationContext,"1")
                    .setSmallIcon(R.drawable.notification_template_icon_bg)
                    .setContentTitle("Agenda Reminder")
                    .setContentText("Some agenda is ongoing right now !")
                    .setSubText("Tap to see the agenda.")
                    .setLargeIcon(largeBitmap)
            var notificationLine = NotificationCompat.InboxStyle()
            do {
                var sb:SpannableString = SpannableString(dataVar.titleAvailable[Iteration]).apply {
                    setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                notificationLine.addLine("${sb} is active agenda.")
                Iteration++
            }while(Iteration < dataVar.titleAvailable.size)
            notificationSchedule.setStyle(notificationLine
                    .setBigContentTitle("${dataVar.titleAvailable.size} Agenda needs to be attend")
                    .setSummaryText("${FirebaseLoginActivity().FgetSharedPreferenceforWorld(applicationContext)}"))
                    .setGroupSummary(false)
                    .build()
                    .flags = Notification.FLAG_NO_CLEAR
            managerNot.notify(0,notificationSchedule.build())
        }
    }
    fun setAvailableSchedule(title:ArrayList<String>){
        dataVar.titleAvailable = title
        getAvailableSchedule()
    }

    override fun startForegroundService(service: Intent?): ComponentName? {
        Log.i("toast","Successfully")
        android.os.Handler().postDelayed({
            startService(service)
        },60000)
        return super.startForegroundService(service)
    }
}