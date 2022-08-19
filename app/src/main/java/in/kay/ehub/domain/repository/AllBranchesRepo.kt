package `in`.kay.ehub.domain.repository

interface AllBranchesRepo {
    suspend fun getAllBranchesList(): List<String>
}