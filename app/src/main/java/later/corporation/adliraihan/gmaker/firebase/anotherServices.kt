package later.corporation.adliraihan.gmaker.firebase

import android.widget.Toast

class anotherServices : Runnable{
    override fun run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
        println("STATUS EAKEUN")
    }
}