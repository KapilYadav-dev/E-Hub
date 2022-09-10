package `in`.kay.ehub.domain.model


import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("company")
    val company: String = "",
    @SerializedName("eventDate")
    val eventDate: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("eventCode")
    val eventCode: String = "",
    @SerializedName("eventName")
    val eventName: String = "",
    @SerializedName("mentorImage")
    val mentorImage: List<String> = listOf(),
    @SerializedName("mentorName")
    val mentorName: String = "",
    @SerializedName("position")
    val position: String = "",
    @SerializedName("posterUrl")
    val posterUrl: String = ""
)