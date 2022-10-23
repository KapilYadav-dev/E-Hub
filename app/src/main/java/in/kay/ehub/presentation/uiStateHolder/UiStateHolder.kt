package `in`.kay.ehub.presentation.uiStateHolder

data class UiStateHolder(
    val isLoading: Boolean = false,
    val data: Any? = null,
    val error:String=""
)
