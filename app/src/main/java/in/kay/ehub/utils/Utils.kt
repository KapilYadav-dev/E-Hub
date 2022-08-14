package `in`.kay.ehub.utils

import android.text.TextUtils
import android.util.Patterns

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


}
