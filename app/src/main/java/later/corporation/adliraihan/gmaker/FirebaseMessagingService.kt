package later.corporation.adliraihan.gmaker

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import later.corporation.adliraihan.gmaker.firebase.FirebaseLoginActivity
import later.corporation.adliraihan.gmaker.firebase.RSSPullService


open class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message:RemoteMessage){
        super.onMessageReceived(message)

        var builderNotification = NotificationCompat.Builder(this,"0")
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        var notManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notManager.notify(0,builderNotification.build())

        Toast.makeText(this,"Dibuat oleh Adli Raihan \n github.com/Thibobs", Toast.LENGTH_SHORT).show()

    }

}