package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Internship
import `in`.kay.ehub.domain.model.Mentors
import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.domain.repository.home.CoursesRepo
import `in`.kay.ehub.domain.repository.home.InternshipRepo
import `in`.kay.ehub.domain.repository.home.MentorsRepo
import `in`.kay.ehub.domain.repository.home.ResourceRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class MentorsRepoImpl @Inject constructor(private val apiService: ApiService) : MentorsRepo,
    SafeApiRequest() {

    override suspend fun getMentors(): List<Mentors> {
        return safeApiRequest { apiService.getMentors() }
    }
}