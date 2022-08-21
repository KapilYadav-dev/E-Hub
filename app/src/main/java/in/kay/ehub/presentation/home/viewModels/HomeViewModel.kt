package `in`.kay.ehub.presentation.home.viewModels

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.usecase.home.GetNewsUseCase
import `in`.kay.ehub.presentation.uiStateHolder.UiStateHolder
import `in`.kay.ehub.utils.Constants
import `in`.kay.ehub.utils.Resource
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {

    var newsList = mutableStateOf(emptyList<News>())

    val newsStateList = mutableStateOf(UiStateHolder())

    init {
        getNews()
    }

    private fun getNews() {
        Log.d(Constants.TAG, "Home: Calling api")
        newsUseCase().onEach {
            when (it) {
                is Resource.Error -> newsStateList.value = UiStateHolder(isLoading = true)
                is Resource.Loading -> newsStateList.value = UiStateHolder(data = it.data)
                is Resource.Success -> newsStateList.value =
                    UiStateHolder(error = it.message.toString())
            }
        }.launchIn(viewModelScope)
    }

}