package `in`.kay.ehub.domain.repository.auth

interface AllBranchesRepo {
    suspend fun getAllBranchesList(): List<String>
}