package later.corporation.adliraihan.gmaker.firebase

import android.content.Context
import android.renderscript.Sampler
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import java.io.IOException

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

    // ================================================================ //
    // ==          READ DATA ON COMMIT WITH SHARED PREFERENCE                ==//
    // ================================================================ /
    fun onRead(){
        var Pref = variables.myRead.child("message")
        val functionImplements = Pref.orderByChild("messageUser")
        functionImplements.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                //Public function if error
            }

            override fun onDataChange(p0: DataSnapshot) {
                val children = p0.children
                println("Adi : " + p0.children.count().toString())
                println("VALUE COSTUM : " + p0.child("messageUser").value)
            }

        })
    }

    fun fail():Boolean{
        return false;
    }

    fun onWrite(){
        var iUsername = "adliraihan"
        var iPassword = "asd123456789ss"
        var iPath = "user_" + iUsername
        onWriteCommitdataUser(iUsername,iPassword,iPath)
        onWriteCommitAgenda(iPath)
    }
    // ================================================================ //
    // ==          WRITE DATA ON COMMIT WITH SHARED PREFERENCE                == //
    // ================================================================ //
    fun onWriteCommitdataUser(
                      username:String?,password:String?,
                              pathParent:String?){
        var accountStoreUsername = variables.dbConnection.getReference(pathParent + url.user_account + "username")
        var accountStorePassword = variables.dbConnection.getReference(pathParent +  url.user_account + "password")
        if(username.equals(null) || password.equals(null)){
            throw IOException ("Tolong isi Seluruhnya")
        }else{
            accountStoreUsername.setValue(username);accountStorePassword.setValue(password)
        }
    }
    fun onWriteCommitAgenda(pathParent: String?){
        var i = 0
        do{
            var agendaStoreTitle = variables.dbConnection.getReference(pathParent + url.user_agenda
                    + "Beli Arduino" +  i.toString() +"/title")
            var agendaStoreDesc = variables.dbConnection.getReference(pathParent + url.user_agenda
                    + "Beli Arduino" +  i.toString() + "/description")
            var agendaStoreDate = variables.dbConnection.getReference(pathParent + url.user_agenda
                    + "Beli Arduino" +  i.toString() + "/date")
            var agendaStoreTime = variables.dbConnection.getReference(pathParent + url.user_agenda
                    + "Beli Arduino" +  i.toString() + "/time")
            var agendaStoretype = variables.dbConnection.getReference(pathParent + url.user_agenda
                    + "Beli Arduino" +  i.toString() + "/type")
            agendaStoreTitle.setValue("Agenda Beta Test " + i.toString())
            agendaStoreDesc.setValue("Beli arduino di plaza yang harganya Rp 420.000,00 ")
            agendaStoreTime.setValue("6:30 PM")
            agendaStoreDate.setValue("12 Mar 2019")
            agendaStoretype.setValue("Important")
            i++
        }while(i <= 10)
    }


    fun onSharedPref(){
        //Fungsi akan dipanggil dari login
    }


}