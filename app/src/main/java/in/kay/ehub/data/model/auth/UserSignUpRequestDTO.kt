package `in`.kay.ehub.data.model.auth

data class UserSignUpRequestDTO(
    val userName:String,
    val email:String,
    val institutionName:String,
    val branch:String,
    val mobile:String,
    val password:String,
    val confirmPassword:String
) {
    constructor() : this("","","","","","","")
}
