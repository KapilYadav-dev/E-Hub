package `in`.kay.ehub.domain.model

import com.google.gson.annotations.SerializedName

data class Mentor(
    @SerializedName("__v")
    val __v: Int,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("about")
    val about: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("linkedinUrl")
    val linkedinUrl: String,
    @SerializedName("mentorDomain")
    val mentorDomain: String,
    @SerializedName("mentorImage")
    val mentorImage: String,
    @SerializedName("mentorName")
    val mentorName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)