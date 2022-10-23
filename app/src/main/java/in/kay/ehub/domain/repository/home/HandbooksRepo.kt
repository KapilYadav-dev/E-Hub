package `in`.kay.ehub.domain.repository.home

import `in`.kay.ehub.domain.model.Handbook

interface HandbooksRepo {
    suspend fun getHandbooks(): List<Handbook>
}