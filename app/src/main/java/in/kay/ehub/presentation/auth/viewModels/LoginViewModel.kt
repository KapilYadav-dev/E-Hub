package `in`.kay.ehub.presentation.auth.viewModels

import `in`.kay.ehub.data.model.auth.UserSignInRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.usecase.auth.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    /*
     * String variables
     */
    var mEmail = mutableStateOf("")
    var mPassword = mutableStateOf("")
    /*
     * Boolean variables
     */
    var isEnabled = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var isLoginClicked = mutableStateOf(false)
    var isSignupClicked = mutableStateOf(false)
    /*
     * Api calls data holder variables
     */
    var userData = mutableStateOf(User())
    var userSignInRequestDTO = mutableStateOf(UserSignInRequestDTO())
    val user = mutableStateOf(UiStateHolder())

    fun userSignIn(userSignInRequestDTO: UserSignInRequestDTO) {
        Log.d(Constants.TAG, "userLogin: Calling api")
        loginUseCase.setUserData(userSignInRequestDTO)
        loginUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    Log.d(Constants.TAG, "userLogin: loading")
                    user.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d(Constants.TAG, "userLogin: Success and data is ${it.data}")
                    user.value = UiStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    Log.d(Constants.TAG, "userLogin: failure and data is ${it.message.toString()}")
                    user.value = UiStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetVariables() {
        mEmail.value=""
        mPassword.value=""
    }

}