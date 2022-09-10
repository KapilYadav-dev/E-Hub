package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.Handbook
import `in`.kay.ehub.domain.repository.home.HandbooksRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class HandbooksRepoImpl @Inject constructor(private val apiService: ApiService) : HandbooksRepo,
    SafeApiRequest() {
    override suspend fun getHandbooks(): List<Handbook> {
        return safeApiRequest { apiService.getHandbooks() }
    }
}