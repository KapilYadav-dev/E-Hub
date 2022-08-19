package `in`.kay.ehub.data.network

import `in`.kay.ehub.BuildConfig
import `in`.kay.ehub.data.model.auth.*
import `in`.kay.ehub.data.model.home.NewsResponseDTO
import `in`.kay.ehub.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsApiService {

    @GET("news?apikey=${BuildConfig.NEWS_API_KEY}&language=en&category=technology")
    suspend fun getTechNews(): Response<NewsResponseDTO>

}