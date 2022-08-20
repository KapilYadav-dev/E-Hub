package `in`.kay.ehub.utils

import `in`.kay.ehub.utils.Constants.TAG
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> safeApiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val responseErr = response.errorBody()?.string()
            Log.d(TAG, "safeApiRequest Error body: ${responseErr.toString()}")
            val message = StringBuilder()
            responseErr.let {
                try {
                    message.append(it?.let { it1 -> JSONObject(it1).getString("msg") })
                } catch (e: JSONException) {
                    Log.d(TAG, "safeApiRequest try catcg: $message")
                }
            }
            Log.d(TAG, "safeApiRequest: $message")
            throw Exception(message.toString())
        }
    }

}