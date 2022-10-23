package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.CampusActivities

interface CampusRepo {
    suspend fun getCampusActivities(): List<CampusActivities>
}