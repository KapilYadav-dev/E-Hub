package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Internship
import `in`.kay.ehub.domain.repository.home.CoursesRepo
import `in`.kay.ehub.domain.repository.home.InternshipRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class InternshipRepoImpl @Inject constructor(private val apiService: ApiService) : InternshipRepo,
    SafeApiRequest() {

    override suspend fun getInternships(): List<Internship> {
        return safeApiRequest { apiService.getInternships() }
    }
}