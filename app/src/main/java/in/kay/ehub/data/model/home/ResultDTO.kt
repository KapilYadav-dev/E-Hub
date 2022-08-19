package `in`.kay.ehub.data.model.home


import com.google.gson.annotations.SerializedName

data class ResultDTO(
    @SerializedName("category")
    val category: List<String> = listOf(),
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("country")
    val country: List<String> = listOf(),
    @SerializedName("creator")
    val creator: List<String> = listOf(),
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("keywords")
    val keywords: List<String>? = null,
    @SerializedName("language")
    val language: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("pubDate")
    val pubDate: String = "",
    @SerializedName("source_id")
    val sourceId: String = "",
    @SerializedName("title")
    val title: String = ""
)