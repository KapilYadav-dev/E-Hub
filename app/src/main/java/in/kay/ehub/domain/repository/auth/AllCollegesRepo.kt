package `in`.kay.ehub.domain.repository.auth

interface AllCollegesRepo {
    suspend fun getAllCollegesList(): List<String>
}