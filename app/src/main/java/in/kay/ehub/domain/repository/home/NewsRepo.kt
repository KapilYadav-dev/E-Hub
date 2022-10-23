package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.data.model.home.NewsResponseDTO
import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.model.User

interface NewsRepo {
    suspend fun getNews(): List<News>
}