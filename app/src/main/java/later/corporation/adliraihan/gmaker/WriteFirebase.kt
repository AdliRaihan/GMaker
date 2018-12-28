package later.corporation.adliraihan.gmaker

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.FileInputStream

class WriteFirebase{
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("message/messageUser")
    val fb = FirebaseDatabase.getInstance()
    val myRead = fb.getReferenceFromUrl("https://gmaker-9c3cb.firebaseio.com/")


    fun intitializeFirebase(konteks:Context){
        FirebaseApp.initializeApp(konteks)
        main()
    }
    fun main(){
        myRef.setValue("Hello Dunia!")
        myRef.setValue("Hello Dunia Sect.2!")

        val dbRead = myRead.child("message")
        val tetekBengek = dbRead.orderByChild("messageUser")
        tetekBengek.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val children = p0.children
                println("Adi : " + p0.children.count().toString())
                println("VALUE COSTUM : " + p0.child("messageUser").value)
                children.forEach(){
                    println(it.toString())
                }
            }

        })

    }
}