package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.domain.model.Resources

interface ResourceRepo {
    suspend fun getResources(): List<Resources>
}