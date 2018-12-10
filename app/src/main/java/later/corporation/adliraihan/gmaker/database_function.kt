package later.corporation.adliraihan.gmaker

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.util.concurrent.TimeUnit

class database_function{
    object coreURL{
        val url = "http://192.168.10.23:81"
        val url_showagenda = url + "/server_config/server_agenda.php"
        var url_costum = url
    }
    object URLBuilder{
        val url = coreURL.url + "/server_config/server_push.php?"
    }
    object databaseConn{
        var okHetepe = OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()
        var okRequest = Request.Builder().url(database_function.coreURL.url).build()
    }
    object JSONReader{
        var gendaJSArray:JSONArray? = null
        var pointer:Int = 0!!
        var getGendaSize = gendaJSArray?.length()!!
        var reader_first = gendaJSArray?.getJSONObject(0)
        var reader_last = gendaJSArray?.getJSONObject(getGendaSize)
        var reader_option = gendaJSArray?.getJSONObject(pointer)
    }
}