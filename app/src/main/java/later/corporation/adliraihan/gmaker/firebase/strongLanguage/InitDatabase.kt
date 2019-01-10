package later.corporation.adliraihan.gmaker.firebase.strongLanguage

import android.content.Context
import android.support.v7.widget.RecyclerView

class InitDatabase{
    object data
    {
        var viewAdapter: RecyclerView.Adapter<*>? = null
    }
    class FirebaseQuery ( private var gotContextParent:Context){
        fun OnRead(){
            var date:ArrayList<String>              = arrayListOf()
            var description:ArrayList<String> = arrayListOf()
            var title:ArrayList<String>               = arrayListOf()
            var type:ArrayList<String>              = arrayListOf()
            println("DATABASE SIZE ${date.size}")
        }
        fun recyclerView() : RecyclerView.Adapter<*>{
            OnRead()
            return data.viewAdapter!!
        }
    }
    // Variable Given //
}