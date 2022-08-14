package `in`.kay.ehub.data.model

data class UserSignUpResponseDTO(
    val userName:String,
    val email:String,
    val institutionName:String,
    val branch:String,
    val mobile:String,
    val accessToken:String,
    val refreshToken:String
)
