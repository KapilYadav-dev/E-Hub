package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.repository.home.NewsRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    private lateinit var newsList: List<News>

    operator fun invoke(): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(newsRepo.getNews()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
