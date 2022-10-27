package `in`.kay.ehub.utils

import `in`.kay.ehub.domain.model.Handbook
import `in`.kay.ehub.domain.model.Mentors
import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.presentation.home.screens.home.domainsList
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.utils.Constants.TAG
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    fun isValidEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    //TODO: CHANGE HERE domainsList to actual api later
    fun filterMentors(viewModel:HomeViewModel):List<Mentors>{
       return viewModel.mentorsList.value.filter { resource ->
            val resDomain = resource.mentorDomain.replace("\\s+".toRegex(), "")
                .lowercase(Locale.getDefault())
            val selectedDomain =
                domainsList()[viewModel.itemIndex.value].name.replace(
                    "\\s+".toRegex(),
                    ""
                ).lowercase(Locale.getDefault())

            resDomain == selectedDomain
        }
    }

    fun filterMagazines(viewModel: HomeViewModel):List<Handbook>{
        //TODO: filter the handbooks by their (domain) catagory

        return viewModel.handBookList.value.filter { resource ->
                val resDomain = resource.category.replace("\\s+".toRegex(), "")
                    .lowercase(Locale.getDefault())

                val selectedDomain =
                    domainsList()[viewModel.itemIndex.value].name.replace(
                        "\\s+".toRegex(),
                        ""
                    ).lowercase(Locale.getDefault())

                resDomain == selectedDomain
            }
    }

    //TODO: CHANGE HERE domainsList to actual api later
    fun filterResources(viewModel:HomeViewModel):List<Resources>{
        return viewModel.resourcesList.value.filter { resource ->

            val resDomain = resource.domain.replace("\\s+".toRegex(), "")
                .lowercase(Locale.getDefault())
            val selectedDomain =
                domainsList()[viewModel.itemIndex.value].name.replace(
                    "\\s+".toRegex(),
                    ""
                ).lowercase(Locale.getDefault())

            resDomain == selectedDomain
        }
    }

    fun isValidPassword(password: String): Boolean {
        return !(TextUtils.isEmpty(password) || password.length < 6)
    }

    //added by aayush
    fun getLastNCharsOfString(str: String, n: Int): String? {
        var lastnChars = str
        if (lastnChars.length > n) {
            lastnChars = lastnChars.substring(lastnChars.length - n, lastnChars.length)
        }
        return lastnChars
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


    fun getDaysLeft(eventDate: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(eventDate)
        val millionSeconds = date!!.time - Calendar.getInstance().timeInMillis
        return TimeUnit.MILLISECONDS.toDays(millionSeconds).toString() + " days left"
    }

    fun getDaysLeftInt(eventDate: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(eventDate)
        val millionSeconds = date!!.time - Calendar.getInstance().timeInMillis
        return TimeUnit.MILLISECONDS.toDays(millionSeconds).toInt()
    }

    fun String.toast(context:Context){
        Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
    }

}
