package `in`.kay.ehub.presentation.home.viewModels

import `in`.kay.ehub.data.datastore.UserDatastore
import `in`.kay.ehub.domain.model.*
import `in`.kay.ehub.domain.usecase.home.*
import `in`.kay.ehub.presentation.uiStateHolder.UiStateHolder
import `in`.kay.ehub.utils.Resource
import android.content.Context
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
    private val videosUseCase: GetVideosUseCase,
    private val eventsUseCase: GetEventsUseCase,
    private val campusActivitiesUseCase: GetCampusActivitiesUseCase,
    private val handbookUseCase: GetHandbooksUseCase
) : ViewModel() {

    var eventsList = mutableStateOf(emptyList<Events>())
    var campusActivitiesList = mutableStateOf(emptyList<CampusActivities>())
    var handBookList = mutableStateOf(emptyList<Handbook>())
    var newsList = mutableStateOf(emptyList<News>())
    var videoList = mutableStateOf(emptyList<YoutubeData>())
    var itemIndex = mutableStateOf(0)
    private var userData = mutableStateOf(User())
    val newsStateList = mutableStateOf(UiStateHolder())
    val videoStateList = mutableStateOf(UiStateHolder())
    var eventStateList = mutableStateOf(UiStateHolder())
    var campusActivitiesStateList = mutableStateOf(UiStateHolder())
    var handBookStateList = mutableStateOf(UiStateHolder())

    init {
        getEvents()
        getCampusActivities()
        getHandBooks()
        // getNews()
        getInstagramUpdates()
        getVideos()
    }


    private fun getEvents() {
        eventsUseCase().onEach {
            when (it) {
                is Resource.Error -> eventStateList.value =
                    UiStateHolder(error = it.message.toString())
                is Resource.Loading -> eventStateList.value = UiStateHolder(isLoading = true)
                is Resource.Success -> eventStateList.value = UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getCampusActivities() {
        campusActivitiesUseCase().onEach {
            when (it) {
                is Resource.Error -> campusActivitiesStateList.value =
                    UiStateHolder(error = it.message.toString())
                is Resource.Loading -> campusActivitiesStateList.value =
                    UiStateHolder(isLoading = true)
                is Resource.Success -> campusActivitiesStateList.value =
                    UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getHandBooks() {
        handbookUseCase().onEach {
            when (it) {
                is Resource.Error -> handBookStateList.value =
                    UiStateHolder(error = it.message.toString())
                is Resource.Loading -> handBookStateList.value = UiStateHolder(isLoading = true)
                is Resource.Success -> handBookStateList.value = UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getNews() {
        newsUseCase().onEach {
            when (it) {
                is Resource.Error -> newsStateList.value =
                    UiStateHolder(error = it.message.toString())
                is Resource.Loading -> newsStateList.value = UiStateHolder(isLoading = true)
                is Resource.Success -> newsStateList.value = UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getInstagramUpdates() {

    }

    private fun getVideos() {
        videosUseCase().onEach {
            when (it) {
                is Resource.Error -> videoStateList.value =
                    UiStateHolder(error = it.message.toString())
                is Resource.Loading -> videoStateList.value = UiStateHolder(isLoading = true)
                is Resource.Success -> videoStateList.value = UiStateHolder(data = it.data)
            }
        }.launchIn(viewModelScope)
    }

    suspend fun updateUserLocally(user: User, context: Context) {
        userData.value = user
        UserDatastore(context).saveUser(user)
    }

    fun getUser(context: Context): Flow<User> {
        return UserDatastore(context).getUser()
    }


}