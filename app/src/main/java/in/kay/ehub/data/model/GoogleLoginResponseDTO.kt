package `in`.kay.ehub.data.model

data class GoogleLoginResponseDTO(
    val email:String,
    val photoUrl:String?,
    val accessToken:String,
    val refreshToken:String
)