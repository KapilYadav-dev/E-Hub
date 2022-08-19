package `in`.kay.ehub.domain.repository

interface AllCollegesRepo {
    suspend fun getAllCollegesList(): List<String>
}