package `in`.kay.ehub.utils

import android.text.TextUtils
import android.util.Patterns

object Validator {
    /**
     * Validate email address
     *
     * @param email
     * @return true if valid email address
     */
    fun isValidEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    /**
     * Validate password
     *
     * @param password
     * @return true if valid password
     */
    fun isValidPassword(password: String): Boolean {
        return !(TextUtils.isEmpty(password) || password.length < 6)
    }

    /**
     * Validate phone number
     *
     * @param phoneNumber
     * @return true if valid phone number
     */
    fun isValidMobile(phoneNumber: String): Boolean {
        return phoneNumber.length == 10
    }
}