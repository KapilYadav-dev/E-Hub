package `in`.kay.ehub.data.model.home


import com.google.gson.annotations.SerializedName

data class NewsResponseDTO(
    @SerializedName("nextPage")
    val nextPage: Int = 0,
    @SerializedName("results")
    val resultDTOS: List<ResultDTO> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0
)