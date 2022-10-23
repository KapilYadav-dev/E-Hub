package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.NewsApiService
import `in`.kay.ehub.domain.mappers.newsToDomain
import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.repository.home.NewsRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val newsApiService: NewsApiService): NewsRepo, SafeApiRequest() {
    override suspend fun getNews(): List<News> {
        val response = safeApiRequest { newsApiService.getTechNews() }
        return response.resultDTOS.newsToDomain()
    }
}