package `in`.kay.ehub.domain.repository.auth

import `in`.kay.ehub.data.model.auth.UserVerifyOtpRequestDTO
import `in`.kay.ehub.data.model.auth.UserVerifyOtpResponseDTO
import `in`.kay.ehub.data.model.auth.UserSignInRequestDTO
import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User

interface UserAuthRepo {
    suspend fun signUp(userSignUpRequestDTO: UserSignUpRequestDTO): User
    suspend fun login(userSignInRequestDTO: UserSignInRequestDTO) : User
    suspend fun verifyOtp(otpBody: UserVerifyOtpRequestDTO): UserVerifyOtpResponseDTO
}