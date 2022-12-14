package `in`.kay.ehub.data.repository.auth

import `in`.kay.ehub.data.model.auth.UserVerifyOtpRequestDTO
import `in`.kay.ehub.data.model.auth.UserVerifyOtpResponseDTO
import `in`.kay.ehub.data.model.auth.UserSignInRequestDTO
import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.mappers.userDetailsToDomain
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.domain.repository.auth.UserAuthRepo
import `in`.kay.ehub.utils.Constants.TAG
import `in`.kay.ehub.utils.SafeApiRequest
import android.util.Log
import javax.inject.Inject

class UserAuthRepoImpl @Inject constructor(private val apiService: ApiService) : UserAuthRepo,
    SafeApiRequest() {

    override suspend fun signUp(userSignUpRequestDTO: UserSignUpRequestDTO): User {
        Log.d(TAG, "signUp: data is $userSignUpRequestDTO")
        val response = safeApiRequest { apiService.userSignUp(userSignUpRequestDTO) }
        return response.userDetailsToDomain()
    }

    override suspend fun login(userSignInRequestDTO: UserSignInRequestDTO): User {
        Log.d(TAG, "signUp: data is $userSignInRequestDTO")
        val response = safeApiRequest { apiService.userSignIn(userSignInRequestDTO) }
        return response.userDetailsToDomain()
    }

    override suspend fun verifyOtp(otpBody: UserVerifyOtpRequestDTO): UserVerifyOtpResponseDTO {
        Log.d(TAG, "verifyOTp: data is $otpBody")
        return safeApiRequest { apiService.verifyOtp(otpBody) }
    }
}