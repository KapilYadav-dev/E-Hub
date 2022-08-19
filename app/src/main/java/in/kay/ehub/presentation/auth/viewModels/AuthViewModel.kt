package `in`.kay.ehub.presentation.auth.viewModels

import `in`.kay.ehub.data.model.UserSignUpRequestDTO
import `in`.kay.ehub.domain.usecase.auth.GetBranchUseCase
import `in`.kay.ehub.domain.usecase.auth.GetCollegeUseCase
import `in`.kay.ehub.domain.usecase.auth.SignUpUseCase
import `in`.kay.ehub.presentation.stateHolder.StateHolder
import `in`.kay.ehub.utils.Constants.TAG
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
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val branchUseCase: GetBranchUseCase,
    private val collegeUseCase: GetCollegeUseCase
) : ViewModel() {

    val user = mutableStateOf(StateHolder())
    val collegeList = mutableStateOf(StateHolder())
    val branchList = mutableStateOf(StateHolder())

    init {
        getAllColleges()
        getAllBranches()
    }

    fun userSignUp(userSignUpRequestDTO: UserSignUpRequestDTO) {
        signUpUseCase.setUserData(userSignUpRequestDTO)
        signUpUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    Log.d(TAG, "userSignUp: ${userSignUpRequestDTO.email}")
                    Log.d(TAG, "userSignUp: loading")
                    user.value = StateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    Log.d(TAG, "userSignUp: Sucess and data is ${it.data}")
                    user.value = StateHolder(data = it.data)
                }
                is Resource.Error -> {
                    Log.d(TAG, "userSignUp: failure and data is ${it.message.toString()}")
                    user.value = StateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllBranches() {
        branchUseCase().onEach {
            when(it) {
                is Resource.Loading -> {
                    branchList.value = StateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    branchList.value = StateHolder(data = it.data)
                }
                is Resource.Error -> {
                    branchList.value = StateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllColleges() {
        collegeUseCase().onEach {
            when(it) {
                is Resource.Loading -> {
                    collegeList.value = StateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    collegeList.value = StateHolder(data = it.data)
                }
                is Resource.Error -> {
                    collegeList.value = StateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}