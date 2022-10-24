package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Internship
import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.domain.repository.home.CoursesRepo
import `in`.kay.ehub.domain.repository.home.InternshipRepo
import `in`.kay.ehub.domain.repository.home.ResourceRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class ResourcesRepoImpl @Inject constructor(private val apiService: ApiService) : ResourceRepo,
    SafeApiRequest() {

    override suspend fun getResources(): List<Resources> {
        return safeApiRequest { apiService.getResources() }
    }
}