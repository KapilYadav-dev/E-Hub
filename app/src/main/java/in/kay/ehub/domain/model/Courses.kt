package `in`.kay.ehub.domain.model

import com.google.gson.annotations.SerializedName

data class Courses(
    @SerializedName("title")
    val courseTitle: String = "",
    @SerializedName("about")
    val courseAbout:String = "",
    @SerializedName("features")
    val features:List<String> = listOf(),
    @SerializedName("posterUrl")
    val posterUrl:String = "",
    @SerializedName("imageUrl")
    val courseImgUrls:List<String> = listOf(),
    @SerializedName("mentorName")
    val courseMentorName:String = "",
    @SerializedName("mentorImage")
    val courseMentorImage:List<String> = listOf(),
    @SerializedName("position")
    val courseMentorPosition:String = "",
    @SerializedName("company")
    val courseMentorCompany:String = "",
    @SerializedName("syllabus")
    val courseSyllabus:List<Syllabus> = listOf(),
    @SerializedName("createdAt")
    val courseCreatedAt:String = "",
    @SerializedName("updatedAt")
    val courseUpdatedAt:String = ""
)