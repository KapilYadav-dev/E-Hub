package `in`.kay.ehub.presentation.stateHolder

data class StateHolder(
    val isLoading: Boolean = false,
    val data: Any? = null,
    val error:String=""
)
