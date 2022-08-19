package `in`.kay.ehub.domain.usecase

import `in`.kay.ehub.domain.repository.AllBranchesRepo
import `in`.kay.ehub.domain.repository.AllCollegesRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBranchUseCase @Inject constructor(private val getBranchesRepo: AllBranchesRepo) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(getBranchesRepo.getAllBranchesList()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
