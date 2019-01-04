package later.corporation.adliraihan.gmaker.firebase

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.snapshot.EmptyNode.Empty
import kotlinx.android.synthetic.main.activity_landing_recycler_parent_update.*
import later.corporation.adliraihan.gmaker.LandingActivity
import later.corporation.adliraihan.gmaker.R
import java.util.*
import java.util.logging.Handler

class RSSPullService :  IntentService(RSSPullService::class.simpleName){
    override fun onHandleIntent(intent: Intent?) {
        onStartCommand(intent,0,1)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        android.os.Handler().postDelayed({
            var managerNot = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            Database().getAvailableSchedulef(FirebaseLoginActivity().FgetSharedPreferenceforWorld(applicationContext).toString(),applicationContext,managerNot)
            ReminderOnSchedule(managerNot)
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

        if(FirebaseCalendar().getHours().equals("10") &&
                FirebaseCalendar().getLocale().equals("PM") &&
                FirebaseCalendar().getMinute().toInt() < 30){
            var mBuilder = NotificationCompat.Builder(applicationContext, 0.toString())
                    .setSmallIcon(R.drawable.notification_template_icon_bg)
                    .setContentTitle("Schedule Daily Reminder")
                    .setContentText("Apakah anda telah mengisi schedule untuk besok ?")
                    .setPriority(NotificationCompat.FLAG_ONGOING_EVENT)
                    .setContentIntent(penIntent)
                    .build()
            mBuilder.flags  = Notification.FLAG_AUTO_CANCEL
            mNot.notify(0,mBuilder)
        }
    }
    fun AutoDeleteSystem(){
        TODO("NEXT UPDATE INI HARUS SELESAI - Adli Raihan")
    }
    fun ReadingSchedule(){
        TODO("NEXT -> NEXT -> Update (Haruselesaiugha)")
    }
    override fun startForegroundService(service: Intent?): ComponentName? {
        Log.i("toast","Successfully")
        android.os.Handler().postDelayed({
            startService(service)
        },60000)
        return super.startForegroundService(service)
    }
}