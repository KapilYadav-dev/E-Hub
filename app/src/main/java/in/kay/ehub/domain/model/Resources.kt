package `in`.kay.ehub.domain.model

import com.google.gson.annotations.SerializedName

data class Resources(
    @SerializedName("__v")
    val __v: Int,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("resourceLink")
    val resourceLink: String,
    @SerializedName("resourceName")
    val resourceName: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)