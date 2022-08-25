package `in`.kay.ehub.data.model.auth

data class UserVerifyOtpRequestDTO(
    val otp: String,
    val accessToken: String
)
