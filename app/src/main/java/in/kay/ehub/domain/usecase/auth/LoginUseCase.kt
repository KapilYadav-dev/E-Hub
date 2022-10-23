package `in`.kay.ehub.domain.usecase.auth

import `in`.kay.ehub.data.model.auth.UserSignInRequestDTO
import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.repository.auth.UserAuthRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userAuthRepo: UserAuthRepo) {
    private var userSignInRequestDTO: UserSignInRequestDTO? =null

    fun setUserData(userSignUpRequestDTO: UserSignInRequestDTO) {
        this.userSignInRequestDTO = userSignUpRequestDTO
    }
    operator fun invoke(): Flow<Resource<User>> = flow {
        emit(Resource.Loading("Loading"))
        delay(2000)
        kotlin.runCatching {
            emit(Resource.Success(userAuthRepo.login(userSignInRequestDTO!!)))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
