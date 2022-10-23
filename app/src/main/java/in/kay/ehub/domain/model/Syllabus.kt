package `in`.kay.ehub.domain.model

import com.google.gson.annotations.SerializedName

data class Syllabus(
    @SerializedName("day")
    val day:Int=-1,
    @SerializedName("title")
    val title:String="",
    @SerializedName("description")
    val description:String="",
    @SerializedName("url")
    val url:String=""

)
