package `in`.kay.ehub.domain.repository

import `in`.kay.ehub.data.model.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User

interface UserAuthRepo {
    suspend fun signUp(userSignUpRequestDTO: UserSignUpRequestDTO): User
}