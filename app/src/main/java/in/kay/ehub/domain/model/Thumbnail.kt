package `in`.kay.ehub.domain.model


import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("imgUrl")
    val imgUrl: String = "",
    @SerializedName("quality")
    val quality: String = ""
)