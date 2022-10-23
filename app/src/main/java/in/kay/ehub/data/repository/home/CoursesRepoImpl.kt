package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.repository.home.CoursesRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class CoursesRepoImpl @Inject constructor(private val apiService: ApiService) : CoursesRepo,
    SafeApiRequest() {

    override suspend fun getCourses(): List<Courses> {
        return safeApiRequest { apiService.getCourse() }
    }
}