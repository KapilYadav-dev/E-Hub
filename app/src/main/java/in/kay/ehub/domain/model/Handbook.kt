package `in`.kay.ehub.domain.model


import com.google.gson.annotations.SerializedName

data class Handbook(
    @SerializedName("_id")
    val id:String = "",
    @SerializedName("description")
    val description:String = "",
    @SerializedName("bookTitle")
    val bookTitle: String = "",
    @SerializedName("bookTagline")
    val bookTagline: String = "",
    @SerializedName("category")
    val category: String = "",
    @SerializedName("pdfUrl")
    val pdfUrl: String = "",
    @SerializedName("imageUrl")
    val imageUrl: List<String> = listOf(),
    @SerializedName("bookimgUrl")
    val bookimgUrl: String = "",
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = ""

)