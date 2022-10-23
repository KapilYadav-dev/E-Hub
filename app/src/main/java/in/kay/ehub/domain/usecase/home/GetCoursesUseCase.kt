package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.CampusActivities
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.repository.home.CoursesRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(private val courseRepo: CoursesRepo) {

    operator fun invoke(): Flow<Resource<List<Courses>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(courseRepo.getCourses()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
