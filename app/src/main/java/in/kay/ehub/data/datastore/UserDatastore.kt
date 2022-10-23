package `in`.kay.ehub.data.datastore

import `in`.kay.ehub.domain.model.User
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDatastore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("User")
        private val USER_NAME = stringPreferencesKey("userName")
        private val USER_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
        private val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        private val REFRESH_TOKEN = stringPreferencesKey("refreshToken")
        private val BRANCH = stringPreferencesKey("branch")
        private val EMAIL = stringPreferencesKey("email")
        private val INSTITUTION_NAME = stringPreferencesKey("institutionName")
        private val MOBILE = stringPreferencesKey("mobile")
    }

    fun getUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_LOGGED_IN] == true
        }
    }

    suspend fun setUserLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_LOGGED_IN] = isLoggedIn
        }
    }

    fun getUser(): Flow<User> = context.dataStore.data
        .map { preferences ->
            User(
                userName = preferences[USER_NAME] ?: "",
                accessToken = preferences[ACCESS_TOKEN] ?: "",
                refreshToken = preferences[REFRESH_TOKEN] ?: "",
                mobile = preferences[MOBILE] ?: "",
                branch = preferences[BRANCH] ?: "",
                email = preferences[EMAIL] ?: "",
                institutionName = preferences[INSTITUTION_NAME] ?: "",
            )
        }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = user.userName
            preferences[ACCESS_TOKEN] = user.accessToken
            preferences[REFRESH_TOKEN] = user.refreshToken
            preferences[MOBILE] = user.mobile
            preferences[BRANCH] = user.branch
            preferences[EMAIL] = user.email
            preferences[INSTITUTION_NAME] = user.institutionName
        }
    }
}