package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.Internship


interface InternshipRepo {
    suspend fun getInternships():List<Internship>
}