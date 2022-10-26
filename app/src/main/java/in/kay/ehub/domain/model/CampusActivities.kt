package `in`.kay.ehub.domain.model


import com.google.gson.annotations.SerializedName

data class CampusActivities(
    @SerializedName("collegeName")
    val collegeName: String = "",
    @SerializedName("collegePhoto")
    val collegePhoto: List<String> = listOf(),
    @SerializedName(" condition")
    val condition: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("eventDate")
    val eventDate: String? = "",
    @SerializedName("eventName")
    val eventName: String = "",
    @SerializedName("eventType")
    val eventType: String = "",
    @SerializedName("price")
    val price: String = ""
)