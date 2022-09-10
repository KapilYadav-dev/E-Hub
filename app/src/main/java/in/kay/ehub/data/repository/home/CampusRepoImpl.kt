package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.CampusActivities
import `in`.kay.ehub.domain.repository.home.CampusRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class CampusRepoImpl @Inject constructor(private val apiService: ApiService) : CampusRepo,
    SafeApiRequest() {
    override suspend fun getCampusActivities(): List<CampusActivities> {
        return safeApiRequest { apiService.getCampusActivities() }
    }
}