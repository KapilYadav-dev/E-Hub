package `in`.kay.ehub.domain.model


import `in`.kay.ehub.data.model.home.youtube.Thumbnail
import com.google.gson.annotations.SerializedName

data class YoutubeData(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("publishedAt")
    val publishedAt: String = "",
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail> = listOf(),
    @SerializedName("videoID")
    val videoID: String = "",
    @SerializedName("videoTitle")
    val videoTitle: String = ""
)