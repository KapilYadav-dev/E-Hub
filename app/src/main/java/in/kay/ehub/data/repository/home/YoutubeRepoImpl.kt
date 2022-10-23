package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.mappers.userDetailsToDomain
import `in`.kay.ehub.domain.mappers.youtubeVideoToDomain
import `in`.kay.ehub.utils.SafeApiRequest
import `in`.kay.ehub.domain.model.YoutubeData
import `in`.kay.ehub.domain.repository.home.YoutubeRepo
import javax.inject.Inject

class YoutubeRepoImpl @Inject constructor(private val apiService: ApiService) : YoutubeRepo,
    SafeApiRequest() {
    override suspend fun getVideos(): List<YoutubeData> {
        val response = safeApiRequest { apiService.getYoutubeVideos() }
        return response.youtubeVideoToDomain()
    }
}