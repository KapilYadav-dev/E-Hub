package `in`.kay.ehub.domain.model


data class News(

    val category: List<String> = listOf(),

    val content: String? = null,

    val description: String = "",

    val imageUrl: String? = null,

    val keywords: List<String>? = null,

    val link: String = "",

    val pubDate: String = "",

    val title: String = "",

)