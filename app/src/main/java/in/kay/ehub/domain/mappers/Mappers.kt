package `in`.kay.ehub.domain.mappers

import `in`.kay.ehub.data.model.auth.UserSignUpResponseDTO
import `in`.kay.ehub.data.model.home.ResultDTO
import `in`.kay.ehub.domain.model.News
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

fun List<ResultDTO>.newsToDomain(): List<News> {
    return map {
        News(
            category = it.category,
            content = it.content,
            title = it.title,
            link = it.link,
            pubDate = it.pubDate,
            description = it.description,
            imageUrl = it.imageUrl,
            keywords = it.keywords,
        )
    }
}
