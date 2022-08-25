package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.model.YoutubeData

interface YoutubeRepo {
    suspend fun getVideos(): List<YoutubeData>
}