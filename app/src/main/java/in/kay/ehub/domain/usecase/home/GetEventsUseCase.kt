package `in`.kay.ehub.domain.usecase.home

import `in`.kay.ehub.domain.model.Events
import `in`.kay.ehub.domain.repository.home.EventsRepo
import `in`.kay.ehub.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepo: EventsRepo) {

    operator fun invoke(): Flow<Resource<List<Events>>> = flow {
        emit(Resource.Loading("Loading"))
        kotlin.runCatching {
            emit(Resource.Success(eventsRepo.getEvents()))
        }.exceptionOrNull()?.let {
            emit(Resource.Error(it.message))
        }
    }
}
