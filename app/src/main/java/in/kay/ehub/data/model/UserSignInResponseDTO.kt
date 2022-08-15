package `in`.kay.ehub.data.model

data class UserSignInResponseDTO(
    val userName:String,
    val email:String,
    val institutionName:String,
    val branch:String,
    val mobile:String,
    val isVerified:Boolean,
    val accessToken:String,
    val refreshToken:String
)
