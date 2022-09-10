package `in`.kay.ehub.domain.model


import com.google.gson.annotations.SerializedName

data class Handbook(
    @SerializedName("bookTagline")
    val bookTagline: String = "",
    @SerializedName("bookTitle")
    val bookTitle: String = "",
    @SerializedName("bookimgUrl")
    val bookimgUrl: String = "",
    @SerializedName("category")
    val category: String = "",
    @SerializedName("imageUrl")
    val imageUrl: List<String> = listOf(),
    @SerializedName("pdfUrl")
    val pdfUrl: String = ""
)