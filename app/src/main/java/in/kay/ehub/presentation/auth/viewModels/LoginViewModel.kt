package `in`.kay.ehub.presentation.auth.viewModels

import `in`.kay.ehub.data.model.auth.UserSignInRequestDTO
import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.usecase.auth.GetBranchUseCase
import `in`.kay.ehub.domain.usecase.auth.GetCollegeUseCase
import `in`.kay.ehub.domain.usecase.auth.LoginUseCase
import `in`.kay.ehub.domain.usecase.auth.SignUpUseCase
import `in`.kay.ehub.presentation.stateHolder.StateHolder
import `in`.kay.ehub.utils.Constants
import `in`.kay.ehub.utils.Resource
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.burnoo.compose.rememberpreference.rememberBooleanPreference
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
    val user = mutableStateOf(StateHolder())

    fun userSignIn(userSignInRequestDTO: UserSignInRequestDTO) {
        Log.d(Constants.TAG, "userLogin: Calling api")
        loginUseCase.setUserData(userSignInRequestDTO)
        loginUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    Log.d(Constants.TAG, "userLogin: loading")
                    user.value = StateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d(Constants.TAG, "userLogin: Success and data is ${it.data}")
                    user.value = StateHolder(data = it.data)
                }
                is Resource.Error -> {
                    Log.d(Constants.TAG, "userLogin: failure and data is ${it.message.toString()}")
                    user.value = StateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetVariables() {
        mEmail.value=""
        mPassword.value=""
    }

}