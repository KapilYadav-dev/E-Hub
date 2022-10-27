package `in`.kay.ehub.presentation.home.viewModels

import `in`.kay.ehub.data.datastore.UserDatastore
import `in`.kay.ehub.domain.model.*
import `in`.kay.ehub.domain.usecase.home.*
import `in`.kay.ehub.presentation.uiStateHolder.UiStateHolder
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
    private val videosUseCase: GetVideosUseCase,
    private val eventsUseCase: GetEventsUseCase,
    private val campusActivitiesUseCase: GetCampusActivitiesUseCase,
    private val handbookUseCase: GetHandbooksUseCase,
    private val coursesUseCase: GetCoursesUseCase,
    private val internshipUseCase: GetInternshipUseCase,
    private val resourcesUseCase: GetResourcesUseCase,
    private val mentorsUseCase: GetMentorsUseCase
) : ViewModel() {

    var eventsList = mutableStateOf(emptyList<Events>())
    var campusActivitiesList = mutableStateOf(emptyList<CampusActivities>())
    var handBookList = mutableStateOf(emptyList<Handbook>())
    var newsList = mutableStateOf(emptyList<News>())
    var videoList = mutableStateOf(emptyList<YoutubeData>())
    var coursesList = mutableStateOf(emptyList<Courses>())
    var mentorsList = mutableStateOf(emptyList<Mentors>())
    var internshipList = mutableStateOf(emptyList<Internship>())
    var resourcesList = mutableStateOf(emptyList<Resources>())

    var itemIndex = mutableStateOf(0)
    var filteredHandbookList = mutableStateOf(emptyList<Handbook>())
    var url = mutableStateOf("")
    private var userData = mutableStateOf(User())

    val newsStateList = mutableStateOf(UiStateHolder())
    val coursesStateList = mutableStateOf(UiStateHolder())
    val videoStateList = mutableStateOf(UiStateHolder())
    var eventStateList = mutableStateOf(UiStateHolder())
    var campusActivitiesStateList = mutableStateOf(UiStateHolder())
    var handBookStateList = mutableStateOf(UiStateHolder())
    var mentorsStateList = mutableStateOf(UiStateHolder())
    var internshipStateList = mutableStateOf(UiStateHolder())
    var resourcesStateList = mutableStateOf(UiStateHolder())

    init {
        getEvents()
        getCampusActivities()
        getHandBooks()
        getNews()
        getVideos()
        getCourses()
        getInternships()
        getResources()
        getMentors()
        getInstagramUpdates()
    }

    private fun getMentors(){
        mentorsUseCase().onEach { result->
            when(result){
                is Resource.Loading -> {
                    mentorsStateList.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success ->{
                    mentorsStateList.value = UiStateHolder(data = result.data)
                }
                is Resource.Error ->{
                    mentorsStateList.value = UiStateHolder(error=result.message?:"")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getResources(){
        resourcesUseCase().onEach { result->
            when(result){
                is Resource.Loading -> {
                    resourcesStateList.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success ->{
                    resourcesStateList.value = UiStateHolder(data = result.data)
                }
                is Resource.Error ->{
                    resourcesStateList.value = UiStateHolder(error=result.message?:"")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getInternships(){
       internshipUseCase().onEach { result->
           Log.d("MAIN",result.data.toString())
           when(result){
               is Resource.Loading -> {
                   internshipStateList.value = UiStateHolder(isLoading = true)
               }
               is Resource.Success ->{
                   internshipStateList.value = UiStateHolder(data = result.data)
               }
               is Resource.Error ->{
                   internshipStateList.value = UiStateHolder(error=result.message?:"")
               }
           }
       }.launchIn(viewModelScope)
    }

    private fun getCourses(){
        coursesUseCase().onEach { result->
            Log.d("MAIN", result.data.toString())
            when(result){
                is Resource.Loading -> {
                    coursesStateList.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success ->{
                    coursesStateList.value = UiStateHolder(data = result.data)
                }
                is Resource.Error ->{
                    coursesStateList.value = UiStateHolder(error=result.message?:"")
                }
            }
        }.launchIn(viewModelScope)
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