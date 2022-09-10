package `in`.kay.ehub.presentation.home.viewModels

import `in`.kay.ehub.data.datastore.UserDatastore
import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.model.YoutubeData
import `in`.kay.ehub.domain.usecase.home.GetNewsUseCase
import `in`.kay.ehub.domain.usecase.home.GetVideosUseCase
import `in`.kay.ehub.presentation.uiStateHolder.UiStateHolder
import `in`.kay.ehub.utils.Constants
import `in`.kay.ehub.utils.Resource
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
    private val videosUseCase: GetVideosUseCase
) : ViewModel() {

    var newsList = mutableStateOf(emptyList<News>())
    var videoList = mutableStateOf(emptyList<YoutubeData>())
    var itemIndex = mutableStateOf(0)
    private var userData = mutableStateOf(User())
    val newsStateList = mutableStateOf(UiStateHolder())
    val videoStateList = mutableStateOf(UiStateHolder())

    init {
        getNews()
        getVideos()
    }

    suspend fun saveUser(user: User, context: Context) {
        userData.value = user
        UserDatastore(context).saveUser(user)
    }

    fun getUser(context: Context): Flow<User> {
        return UserDatastore(context).getUser()
    }

    private fun getVideos() {
        videosUseCase().onEach {
            when (it) {
                is Resource.Error -> videoStateList.value = UiStateHolder(error = it.message.toString())
                is Resource.Loading -> videoStateList.value = UiStateHolder(isLoading = true)
                is Resource.Success -> videoStateList.value = UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getNews() {
        Log.d(Constants.TAG, "Home: Calling api")
        newsUseCase().onEach {
            when (it) {
                is Resource.Error -> newsStateList.value = UiStateHolder(error = it.message.toString())
                is Resource.Loading -> newsStateList.value = UiStateHolder(isLoading = true)
                is Resource.Success -> newsStateList.value = UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

}