package `in`.kay.ehub.presentation.home.viewModels

import `in`.kay.ehub.domain.usecase.home.GetNewsUseCase
import `in`.kay.ehub.presentation.uiStateHolder.UiStateHolder
import `in`.kay.ehub.utils.Resource
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

    val newsList = mutableStateOf(UiStateHolder())

    init {
        getNews()
    }
    private fun getNews() {
        newsUseCase().onEach {
            when (it) {
                is Resource.Error -> newsList.value = UiStateHolder(isLoading = true)
                is Resource.Loading -> newsList.value = UiStateHolder(data = it.data)
                is Resource.Success -> newsList.value = UiStateHolder(error = it.message.toString())
            }
        }.launchIn(viewModelScope)
    }

}