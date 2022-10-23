package `in`.kay.ehub.domain.model

import com.google.gson.annotations.SerializedName

data class Internship(
    @SerializedName("_id")
    val id: String,
    @SerializedName("internCompany")
    val internCompany: String,
    @SerializedName("applyUrl")
    val applyLink: String,
    @SerializedName("internPosition")
    val internPosition: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("timing")
    val timing: String
)