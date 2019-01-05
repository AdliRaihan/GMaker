package later.corporation.adliraihan.gmaker.firebase

import android.annotation.TargetApi
import android.app.usage.NetworkStats
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkSpecifier
import android.net.sip.SipSession
import android.os.AsyncTask
import android.os.Build
import android.os.strictmode.NetworkViolation
import android.text.PrecomputedText
import android.util.Log
import android.widget.Toast

var TAG = "RECEIVER"
class FirebaseReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val tasksync = Task(goAsync(),intent).execute()
        CheckConnectivity(context)
    }

    fun CheckConnectivity(cntx:Context){
        (cntx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo.apply {
            if(this != null && isConnected)
                println(isConnected)
            else if(this == null)
                Toast.makeText(cntx," Cek kembali koneksi internet anda.", Toast.LENGTH_SHORT).show()
        }


        run{
            @TargetApi(Build.VERSION_CODES.LOLLIPOP){
                (cntx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).addDefaultNetworkActiveListener{
                    //SIMPAN BUAT NANTI
                }
            }
        }

    }



    private class Task(
            private val pendingResult: PendingResult,
            private val intent: Intent
    ) : AsyncTask<Void,Void,String>(){
        override fun doInBackground(vararg params: Void?): String {
            return toString().also { log ->
                Log.d(TAG, log)
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pendingResult.finish()
        }

    }



}
