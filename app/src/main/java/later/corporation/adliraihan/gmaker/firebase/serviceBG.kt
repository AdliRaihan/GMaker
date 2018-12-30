package later.corporation.adliraihan.gmaker.firebase

import android.app.NotificationManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import later.corporation.adliraihan.gmaker.R

class serviceBG : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground()
        startService(intent)
        return START_STICKY
    }
    fun startForeground(){
        var mBuilder = NotificationCompat.Builder(this, 0.toString())
        .setSmallIcon(R.drawable.notification_template_icon_bg)
        .setContentTitle("Service Status")
        .setContentText("On Running !")
        .setPriority(NotificationCompat.PRIORITY_MAX)
        val mNotification = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotification.notify(1,mBuilder.build())

    }
    override fun startForegroundService(service: Intent?): ComponentName? {
        var mBuilder = NotificationCompat.Builder(this, 0.toString())
            .setSmallIcon(R.drawable.notification_template_icon_bg)
            .setContentTitle("Service Status")
            .setContentText("On Running !")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        val mNotification = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotification.notify(1,mBuilder.build())
        return super.startForegroundService(service)
    }
}
