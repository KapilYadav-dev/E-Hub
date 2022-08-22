package `in`.kay.ehub.data.model

data class UserVerifyOtpRequestDTO(
    val otp: String,
    val accessToken: String
)
