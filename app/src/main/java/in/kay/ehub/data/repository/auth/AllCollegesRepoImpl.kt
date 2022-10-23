package `in`.kay.ehub.data.repository.auth

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.repository.auth.AllCollegesRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class AllCollegesRepoImpl @Inject constructor(private val apiService: ApiService) : AllCollegesRepo,
    SafeApiRequest() {

    override suspend fun getAllCollegesList(): List<String> {
        return safeApiRequest { apiService.getCollegeList() }
    }
}