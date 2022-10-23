package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.Events

interface EventsRepo {
    suspend fun getEvents(): List<Events>
}