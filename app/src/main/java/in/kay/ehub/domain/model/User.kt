package `in`.kay.ehub.domain.model



data class User(

    val accessToken: String = "",

    val branch: String = "",

    val isAdmin: Boolean = false,

    val email: String = "",

    val institutionName: String = "",

    val mobile: String = "",

    val refreshToken: String = "",

    val userName: String = ""
)