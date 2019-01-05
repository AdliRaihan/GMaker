package later.corporation.adliraihan.gmaker.firebase

import java.lang.Exception

class  CreateDailyFunctionTimer(){

    // Function timer by Adli Raihan Ganteng

    fun main(timer_1HH:String,
             timer_1MM:String,timer_1A:String,
             timer_2HH:String,
             timer_2MM:String,timer_2A:String)
            : Boolean{
        if(
                (timer_1HH.toInt() > 12 || timer_1HH.toInt() < 0) ||
                (timer_2HH.toInt() > 12 || timer_2HH.toInt() < 0) ||
                (timer_1MM.toInt() > 60 || timer_1MM.toInt() < 0) ||
                (timer_2MM.toInt() > 60 || timer_2MM.toInt() < 0 )
        ){
            return false
        }else{
            if(timer_1HH.toInt() ==  timer_1HH.toInt() &&
                    timer_1MM.toInt() == timer_2MM.toInt() &&
                    timer_1A.equals(timer_2A)){
                        return false
            }else{
                return true
            }
        }
        return false
    }

    fun error(Code:Int) : String{
        if(Code == 100)  return "Invalid Format !"
        if(Code == 101) return "Invalid String !"
        return "Invalid Nothing"
    }

}
