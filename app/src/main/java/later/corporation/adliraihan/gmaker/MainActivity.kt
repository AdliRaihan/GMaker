package later.corporation.adliraihan.gmaker

import android.app.NotificationManager
import android.content.*
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.pusher.pushnotifications.PushNotificationReceivedListener
import com.pusher.pushnotifications.PushNotifications
import com.pusher.pushnotifications.PushNotificationsInstance
import com.pusher.pushnotifications.api.PushNotificationsAPI
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import later.corporation.adliraihan.gmaker.firebase.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var receiver:BroadcastReceiver = FirebaseReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrower)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = token
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })

        setupLocalNotifications()
    }


    fun setupLocalNotifications ()
    {

        var builderNotification = NotificationCompat.Builder(this,"0")
            .setContentTitle("Local Notifications")
            .setContentText("Local Notifications")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        var notManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notManager.notify(0,builderNotification.build())

        Toast.makeText(this,"Dibuat oleh Adli Raihan \n github.com/Thibobs", Toast.LENGTH_SHORT).show()

    }


}
