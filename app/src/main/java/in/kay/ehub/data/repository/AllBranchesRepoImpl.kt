package `in`.kay.ehub.data.repository

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.repository.auth.AllBranchesRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class AllBranchesRepoImpl @Inject constructor(private val apiService: ApiService) : AllBranchesRepo,
    SafeApiRequest() {

    override suspend fun getAllBranchesList(): List<String> {
        return safeApiRequest { apiService.getBranchesList() }
    }
}