package `in`.kay.ehub.domain.usecase

import `in`.kay.ehub.data.model.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.repository.UserAuthRepo
import `in`.kay.ehub.utils.Constants.TAG
import `in`.kay.ehub.utils.Resource
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val userAuthRepo: UserAuthRepo) {
    var userSignUpRequestDTO: UserSignUpRequestDTO?=null

    fun setUserData(userSignUpRequestDTO: UserSignUpRequestDTO) {
        this.userSignUpRequestDTO = userSignUpRequestDTO
    }
    operator fun invoke(): Flow<Resource<User>> = flow {
        emit(Resource.Loading("Loading"))
        delay(2000)
        kotlin.runCatching {
            emit(Resource.Success(userAuthRepo.signUp(userSignUpRequestDTO!!)))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
