package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.Mentors
import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.domain.repository.home.MentorsRepo
import `in`.kay.ehub.domain.repository.home.ResourceRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMentorsUseCase @Inject constructor(private val mentorsRepo: MentorsRepo) {

    operator fun invoke(): Flow<Resource<List<Mentors>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(mentorsRepo.getMentors()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
