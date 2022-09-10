package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.Handbook
import `in`.kay.ehub.domain.repository.home.HandbooksRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHandbooksUseCase @Inject constructor(private val handBookRepo: HandbooksRepo) {

    operator fun invoke(): Flow<Resource<List<Handbook>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(handBookRepo.getHandbooks()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
