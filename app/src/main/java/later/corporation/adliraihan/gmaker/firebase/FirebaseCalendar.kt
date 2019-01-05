package later.corporation.adliraihan.gmaker.firebase

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class FirebaseCalendar{
    object cal{
        var m = arrayListOf<String>(
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
        )
        var d = arrayListOf<Int>(
                31,28,31,30,31,30,31,31,30,31,30,31
        )
    }

    fun CompareTwo(FullDateCurrent:String,FullDateTarget:String) : String?{
            Log.i("FULL DATE " , FullDateTarget)
            var targetDate = "01"
            var targetMonth = "Jan"
            var targetYear = "1900"
            try{
                targetDate = FullDateTarget[0].toString() + FullDateTarget[1].toString()
                targetMonth = FullDateTarget[3].toString() + FullDateTarget[4].toString() + FullDateTarget[5].toString()
                targetYear = FullDateTarget[7].toString() + FullDateTarget[8].toString() + FullDateTarget[9].toString() +FullDateTarget[10].toString()
            }
            catch(E:Exception){
              Log.i("Error","Error")
            }
            // Var
            var calendar = Calendar.getInstance().getTime()
            var dateFormat = SimpleDateFormat("dd")
            var currentDate = dateFormat.format(calendar)
            dateFormat = SimpleDateFormat("MMM")
            var currentMonth = dateFormat.format(calendar)
            dateFormat = SimpleDateFormat("yyyy")
            var currentYear = dateFormat.format(calendar)
            // Var
            var targetMonthInt = CalendarToInt(targetMonth)
            var currentMonthInt = CalendarToInt(currentMonth)

            var i = 0
            var totalTargetMonth = 0
            do{
                totalTargetMonth += cal.d[i]
                i++
            }while(i < targetMonthInt)
            var totalCurrentMonth =  0 ; i = 0
            do{
                totalCurrentMonth += cal.d[i]
                i++;
            }while(i < currentMonthInt)
            //INTENSE MATH
            var differentYear = (targetYear.toInt() - currentYear.toInt())
            if(differentYear >=  0) {
                var yearsDay = 0
                var totalCurrent = 0
                var totalTarget = 0
                if(currentYear < targetYear){
                    yearsDay = ((targetYear.toInt() - currentYear.toInt()) + 1 )* 365
                    totalTarget = totalTargetMonth + targetDate.toInt() + yearsDay
                }else{
                    totalTarget = totalTargetMonth + targetDate.toInt() + (differentYear * 365)
                }

                totalCurrent = totalCurrentMonth + currentDate.toInt() + (differentYear * 365)
                var compareDate = totalTarget - totalCurrent
                if(compareDate < 0){
                    return compareDate.toString()
                }else{
                    return compareDate.toString()
                }
            }else{
                return " -1 "
            }
            return null

    }
    fun getDateMonthFromArray(FullDateTarget:String) : String?{
        var targetDate = FullDateTarget[0].toString() + FullDateTarget[1].toString()
        var targetMonth = FullDateTarget[3].toString() + FullDateTarget[4].toString() + FullDateTarget[5].toString()
        return ("$targetDate/$targetMonth")
    }

    fun intToCalendar(number:Int):String
    {
        var numberString :String = ""
        if(number == 1)
            numberString = "Jan"
        if(number == 2)
            numberString = "Feb"
        if(number == 3)
            numberString = "Mar"
        if(number == 4)
            numberString = "Apr"
        if(number == 5)
            numberString = "May"
        if(number == 6)
            numberString = "Jun"
        if(number == 7)
            numberString = "Jul"
        if(number == 8)
            numberString = "Aug"
        if(number == 9)
            numberString = "Sep"
        if(number == 10)
            numberString = "Oct"
        if(number == 11)
            numberString = "Nov"
        if(number == 12)
            numberString = "Dec"

        return numberString
    }
    fun CalendarFullString(number:Int) : String{
            var numberString :String = ""
            if(number == 1)
                numberString = "January"
            if(number == 2)
                numberString = "February"
            if(number == 3)
                numberString = "March"
            if(number == 4)
                numberString = "April"
            if(number == 5)
                numberString = "May"
            if(number == 6)
                numberString = "June"
            if(number == 7)
                numberString = "July"
            if(number == 8)
                numberString = "August"
            if(number == 9)
                numberString = "September"
            if(number == 10)
                numberString = "October"
            if(number == 11)
                numberString = "November"
            if(number == 12)
                numberString = "December"

            return numberString
    }

    fun CalendarToInt(calendar:String):Int{
        var numberInter:Int = 0

        if(calendar.equals("Jan"))
            numberInter = 1
        if(calendar.equals("Feb"))
            numberInter = 2
        if(calendar.equals("Mar"))
            numberInter = 3
        if(calendar.equals("Apr"))
            numberInter = 4
        if(calendar.equals("May"))
            numberInter = 5
        if(calendar.equals("Jun"))
            numberInter = 6
        if(calendar.equals("Jul"))
            numberInter = 7
        if(calendar.equals("Aug"))
            numberInter = 8
        if(calendar.equals("Sep"))
            numberInter = 9
        if(calendar.equals("Oct"))
            numberInter = 10
        if(calendar.equals("Nov"))
            numberInter = 11
        if(calendar.equals("Dec"))
            numberInter = 12

        return numberInter
    }
    fun CalendarEveryMonth(calendar:String):Int{
        var numberInter:Int = 0
        if(calendar.equals("Jan"))
            numberInter = 30
        if(calendar.equals("Feb"))
            numberInter = 29
        if(calendar.equals("Mar"))
            numberInter = 30
        if(calendar.equals("Apr"))
            numberInter = 31
        if(calendar.equals("May"))
            numberInter = 30
        if(calendar.equals("Jun"))
            numberInter = 31
        if(calendar.equals("Jul"))
            numberInter = 30
        if(calendar.equals("Aug"))
            numberInter = 31
        if(calendar.equals("Sep"))
            numberInter = 30
        if(calendar.equals("Oct"))
            numberInter = 31
        if(calendar.equals("Nov"))
            numberInter = 30
        if(calendar.equals("Dec"))
            numberInter = 31

        return numberInter
    }
    fun getHours() : String{
        var h = Calendar.getInstance().time
        var sh = SimpleDateFormat("hh").format(h)
        return sh
    }
    fun getSeconds() : String{
        var h = Calendar.getInstance().time
        var sh = SimpleDateFormat("ss").format(h)
        return sh
    }
    fun getMinute() : String{
        var h = Calendar.getInstance().time
        var sh = SimpleDateFormat("mm").format(h)
        return sh
    }
    fun getLocale() : String{
        var h = Calendar.getInstance().time
        var sh = SimpleDateFormat("a").format(h)
        return sh
    }

}