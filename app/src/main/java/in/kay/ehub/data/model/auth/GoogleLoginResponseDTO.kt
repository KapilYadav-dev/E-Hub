package `in`.kay.ehub.data.model.auth

data class GoogleLoginResponseDTO(
    val email:String,
    val photoUrl:String?,
    val accessToken:String,
    val refreshToken:String
)