package `in`.kay.ehub.presentation.home.viewModels

import `in`.kay.ehub.domain.usecase.home.GetNewsUseCase
import `in`.kay.ehub.presentation.stateHolder.StateHolder
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

    val newsList = mutableStateOf(StateHolder())

    init {
        getNews()
    }
    private fun getNews() {
        newsUseCase().onEach {
            when (it) {
                is Resource.Error -> newsList.value = StateHolder(isLoading = true)
                is Resource.Loading -> newsList.value = StateHolder(data = it.data)
                is Resource.Success -> newsList.value = StateHolder(error = it.message.toString())
            }
        }.launchIn(viewModelScope)
    }

}