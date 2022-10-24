package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.domain.repository.home.ResourceRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResourcesUseCase @Inject constructor(private val resouceRepo: ResourceRepo) {

    operator fun invoke(): Flow<Resource<List<Resources>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(resouceRepo.getResources()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
