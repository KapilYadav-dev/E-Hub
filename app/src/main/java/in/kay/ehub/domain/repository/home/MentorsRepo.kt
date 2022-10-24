package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.Mentors

interface MentorsRepo {
    suspend fun getMentors(): List<Mentors>
}