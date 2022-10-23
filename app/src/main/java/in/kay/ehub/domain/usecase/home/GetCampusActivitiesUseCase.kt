package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.CampusActivities
import `in`.kay.ehub.domain.repository.home.CampusRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCampusActivitiesUseCase @Inject constructor(private val campusRepo: CampusRepo) {

    operator fun invoke(): Flow<Resource<List<CampusActivities>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(campusRepo.getCampusActivities()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
