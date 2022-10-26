package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.Internship

interface InternRepo {
    suspend fun getVideos(): List<Internship>
}