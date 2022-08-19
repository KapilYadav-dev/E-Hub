package `in`.kay.ehub.domain.mappers

import `in`.kay.ehub.data.model.UserSignUpResponseDTO
import `in`.kay.ehub.domain.model.User

fun UserSignUpResponseDTO.userDetailsToDomain(): User {
    return User(
        accessToken = this.accessToken,
        branch = this.branch,
        email = this.email,
        institutionName = this.institutionName,
        mobile = this.mobile,
        refreshToken = this.refreshToken,
        userName = this.userName
    )
}
