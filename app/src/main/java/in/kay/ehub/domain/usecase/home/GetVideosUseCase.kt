package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.model.YoutubeData
import `in`.kay.ehub.domain.repository.home.YoutubeRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(private val videoRepo: YoutubeRepo) {
    operator fun invoke(): Flow<Resource<List<YoutubeData>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(videoRepo.getVideos()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
