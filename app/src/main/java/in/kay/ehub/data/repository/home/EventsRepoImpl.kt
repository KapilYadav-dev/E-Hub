package `in`.kay.ehub.data.repository.home

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.domain.model.Events
import `in`.kay.ehub.domain.repository.home.EventsRepo
import `in`.kay.ehub.utils.SafeApiRequest
import javax.inject.Inject

class EventsRepoImpl @Inject constructor(private val apiService: ApiService) : EventsRepo,
    SafeApiRequest() {
    override suspend fun getEvents(): List<Events> {
        return safeApiRequest { apiService.getEvents() }
    }
}