package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.CampusActivities
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Internship
import `in`.kay.ehub.domain.repository.home.CoursesRepo
import `in`.kay.ehub.domain.repository.home.InternshipRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetInternshipUseCase @Inject constructor(private val internshipRepo: InternshipRepo) {

    operator fun invoke(): Flow<Resource<List<Internship>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(internshipRepo.getInternships()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
