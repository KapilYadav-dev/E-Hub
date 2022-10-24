package `in`.kay.ehub.domain.model

import com.google.gson.annotations.SerializedName

data class Courses(
    @SerializedName("title")
    val courseTitle: String = "",
    //TODO:change about's data type to string and create features field (list of string) later
    @SerializedName("about")
    val courseAbout:List<String> = listOf(),
    @SerializedName("posterUrl")
    val courseUrl:String = "",
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