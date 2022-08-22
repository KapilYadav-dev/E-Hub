package `in`.kay.ehub.data.network

import `in`.kay.ehub.data.model.UserVerifyOtpRequestDTO
import `in`.kay.ehub.data.model.UserVerifyOtpResponseDTO
import `in`.kay.ehub.data.model.auth.*
import `in`.kay.ehub.data.model.home.NewsResponseDTO
import `in`.kay.ehub.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    /*
     * SignUp API
     */
    @POST(Constants.API_SIGN_UP_ROUTE)
    suspend fun userSignUp(
        @Body user: UserSignUpRequestDTO
    ): Response<UserSignUpResponseDTO>

    /*
     * Google Login API
     */
    @POST(Constants.API_GOOGLE_LOGIN_ROUTE)
    suspend fun googleLogin(
        @Body user: GoogleLoginRequestDTO
    ): Response<GoogleLoginResponseDTO>

    /*
     * SignIn API
     */
    @POST(Constants.API_SIGN_IN_ROUTE)
    suspend fun userSignIn(
        @Body user: UserSignInRequestDTO
    ): Response<UserSignInResponseDTO>

    @POST(Constants.API_SIGN_IN_ROUTE)
    suspend fun verifyOtp(
        @Body otpBody: UserVerifyOtpRequestDTO
    ): Response<UserVerifyOtpResponseDTO>

    /*
     * Get all colleges list api
     */
    @GET(Constants.API_GET_ALL_COLLEGES_ROUTE)
    suspend fun getCollegeList(): Response<List<String>>

    /*
     * Get branches list api
     */
    @GET(Constants.API_GET_ALL_BRANCHES_ROUTE)
    suspend fun getBranchesList(): Response<List<String>>

}