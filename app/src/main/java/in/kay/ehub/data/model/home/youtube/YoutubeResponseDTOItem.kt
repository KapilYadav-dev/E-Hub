package `in`.kay.ehub.data.model.home.youtube

data class YoutubeResponseDTOItem(
    val __v: Int,
    val _id: String,
    val channelTitle: String,
    val description: String,
    val publishedAt: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
    val videoId: String
)