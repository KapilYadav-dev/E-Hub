package `in`.kay.ehub.domain.usecase.auth

import `in`.kay.ehub.data.model.UserVerifyOtpRequestDTO
import `in`.kay.ehub.data.model.UserVerifyOtpResponseDTO
import `in`.kay.ehub.domain.repository.auth.UserAuthRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(private val userAuthRepo: UserAuthRepo) {
    private var otpBody: UserVerifyOtpRequestDTO? = null

    fun setOtpData(otpBody: UserVerifyOtpRequestDTO) {
        this.otpBody = otpBody
    }

    operator fun invoke(): Flow<Resource<UserVerifyOtpResponseDTO>> = flow {
        emit(Resource.Loading("Loading"))
        delay(2000)
        kotlin.runCatching {
            emit(Resource.Success(userAuthRepo.verifyOtp(otpBody!!)))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
