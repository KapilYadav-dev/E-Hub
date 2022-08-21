package `in`.kay.ehub.presentation.auth.viewModels

import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.usecase.auth.GetBranchUseCase
import `in`.kay.ehub.domain.usecase.auth.GetCollegeUseCase
import `in`.kay.ehub.domain.usecase.auth.SignUpUseCase
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
class SignupViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val branchUseCase: GetBranchUseCase,
    private val collegeUseCase: GetCollegeUseCase
) : ViewModel() {
    /*
     * String variables
     */
    var mUserName = mutableStateOf("")
    var mEmail = mutableStateOf("")
    var mPassword = mutableStateOf("")
    var mConfirmPassword = mutableStateOf("")
    var mMobile = mutableStateOf("")
    var mSelectedCollege = mutableStateOf("")
    var mSelectedBranch = mutableStateOf("")
    /*
     * Boolean variables
     */
    var isLoading = mutableStateOf(false)
    var isEnabled = mutableStateOf(false)
    var isLoginClicked = mutableStateOf(false)
    var isSignupClicked = mutableStateOf(false)
    /*
     * List variables
     */
    var mCollegeList = mutableStateOf(emptyList<String>())
    var mBranchList = mutableStateOf(emptyList<String>())

    /*
     * Other datatype variables
     */
    var userSignUpRequestDTO = mutableStateOf(UserSignUpRequestDTO())
    var userData = mutableStateOf(User())
    /*
     * Api calls data holder variables
     */
    val user = mutableStateOf(UiStateHolder())
    internal val collegeList = mutableStateOf(UiStateHolder())
    internal val branchList = mutableStateOf(UiStateHolder())

    init {
        getAllColleges()
        getAllBranches()
    }

    fun userSignUp(userSignUpRequestDTO: UserSignUpRequestDTO) {
        Log.d(Constants.TAG, "userSignUp: Calling api")
        signUpUseCase.setUserData(userSignUpRequestDTO)
        signUpUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    Log.d(Constants.TAG, "userSignUp: ${userSignUpRequestDTO.email}")
                    Log.d(Constants.TAG, "userSignUp: loading")
                    user.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d(Constants.TAG, "userSignUp: Sucess and data is ${it.data}")
                    user.value = UiStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    Log.d(Constants.TAG, "userSignUp: failure and data is ${it.message.toString()}")
                    user.value = UiStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllBranches() {
        branchUseCase().onEach {
            when(it) {
                is Resource.Loading -> {
                    branchList.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    branchList.value = UiStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    branchList.value = UiStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllColleges() {
        collegeUseCase().onEach {
            when(it) {
                is Resource.Loading -> {
                    collegeList.value = UiStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    collegeList.value = UiStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    collegeList.value = UiStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetVariables() {
         mUserName.value = ""
         mEmail.value = ""
         mPassword.value = ""
         mConfirmPassword.value = ""
         mMobile.value = ""
         mSelectedCollege.value = ""
         mSelectedBranch.value = ""
    }

}