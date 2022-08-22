package `in`.kay.ehub.presentation.auth.viewModels

import `in`.kay.ehub.data.model.UserVerifyOtpRequestDTO
import `in`.kay.ehub.domain.usecase.auth.VerifyOtpUseCase
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
class VerifyOtpViewModel @Inject constructor(
    private val verifyOtpUseCase: VerifyOtpUseCase
) : ViewModel() {
    /*
     * String variables
     */
    var mAccessToken = mutableStateOf("")
    var mOtp = mutableStateOf("")

    /*
     * Boolean variables
     */
    var isEnabled = mutableStateOf(false)
    var isLoading = mutableStateOf(false)

    /*
     * Api calls data holder variables
     */
    val user = mutableStateOf(UiStateHolder())

    fun verifyOtp(otpBody: UserVerifyOtpRequestDTO) {
        Log.d(Constants.TAG, "userLogin: Calling api")
        verifyOtpUseCase.setOtpData(otpBody)
        verifyOtpUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    Log.d(Constants.TAG, "verifyOtp: loading")
                    user.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d(Constants.TAG, "verifyOtp: Success and data is ${it.data}")
                    user.value = UiStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    Log.d(Constants.TAG, "verifyOtp: failure and data is ${it.message.toString()}")
                    user.value = UiStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}