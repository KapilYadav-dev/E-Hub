package `in`.kay.ehub.utils

import `in`.kay.ehub.utils.Constants.TAG
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

object Utils {

    fun isValidEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    fun isValidPassword(password: String): Boolean {
        return !(TextUtils.isEmpty(password) || password.length < 6)
    }

    fun getDate(publishedAt: String): String {
        val p = PrettyTime()
        val input = SimpleDateFormat("yyyy-MM-dd")
        var d: Date? = null
        runCatching {
            d = input.parse(publishedAt)
        }.getOrElse {
            Log.d(TAG, "exc: ${it.localizedMessage}")

        }
        Log.d(TAG, "getDate: ${d.toString()}")
        val date = d?.let {
            p.format(d)
        }
        return date.orEmpty()
    }

    fun getPresentableDate(timeStamp:String):String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val date = LocalDateTime.parse(timeStamp, formatter)

        val dtf: DateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)

        val ldt: LocalDateTime = date
        return ldt.format(dtf).toString()
    }

}
