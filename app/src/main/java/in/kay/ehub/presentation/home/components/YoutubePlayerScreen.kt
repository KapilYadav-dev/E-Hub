package `in`.kay.ehub.presentation.home.components

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Thumbnail
import `in`.kay.ehub.domain.model.YoutubeData
import `in`.kay.ehub.presentation.auth.components.SecondaryButton
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils.getDate
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun YoutubePlayerScreen(youtubeData: YoutubeData) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = colorWhite)
    ) {
        AndroidView(
            factory = { context ->
                val view =
                    LayoutInflater.from(context).inflate(R.layout.youtube_player_view, null, false)
                val playerView = view.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
                playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId = youtubeData.videoID
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
                view
            },
            update = { view ->
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .height(240.dp)
        )
        Text(
            text = youtubeData.videoTitle.lowercase(),
            style = `in`.kay.ehub.ui.theme.Typography.body1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
        Text(
            text = "450 views • ${getDate(youtubeData.publishedAt)}",
            style = `in`.kay.ehub.ui.theme.Typography.body1,
            color = colorBlack,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        ReadMoreText(
            text = youtubeData.description.lowercase(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            style = `in`.kay.ehub.ui.theme.Typography.body1,
        )
        SecondaryButton(
            "subscribe for more updates",
            modifier = Modifier
                .padding(24.dp)
                .height(56.dp),
            onClick = {},
            buttonIconSize=40.dp,
            painterResource = painterResource(id = R.drawable.ic_youtube)
        )
    }
}

@Preview
@Composable
fun Preview() {
    val data = YoutubeData(
        videoID = "2iAd-tQVf4c",
        videoTitle = "From Tier-3 to International Offer || Whole Journey || engineerHUB #weekendwithus \uD83D\uDD25\uD83D\uDC68\uD83C\uDFFB\u200D\uD83C\uDF93",
        description = "Want to crack an international deal ❓ Watch our #weekendwithus session with Mr. Deepak Sharma (Upcoming SDE at Amazon Dublin). Do you wanna interact with 1v1 interaction with mentors? - Catch us live on Weekend with us. \uD83D\uDD25\uD83D\uDC68\uD83C\uDFFB\u200D\uD83C\uDF93 Join our officials for more updates : Visit us : https://www.engineerhub.in - Telegram : https://t.me/enginnerhub_in - YouTube : https://www.youtube.com/channel/UCXuy8rldhATY0qxxvwglWbw - Discord : https://discord.gg/xNcefnFEVu - Twitter : https://twitter.com/engineerhub_in - LinkedIn: https://www.linkedin.com/company/engineersummit/mycompany/",
        publishedAt = "2022-07-21T11:30:02Z",
        thumbnails = listOf(
            Thumbnail(
                imgUrl = "https://i.ytimg.com/vi/2iAd-tQVf4c/maxresdefault.jpg",
                quality = 1280.toString()
            ),
            Thumbnail(
                imgUrl = "https://i.ytimg.com/vi/2iAd-tQVf4c/sddefault.jpg",
                quality = 640.toString()
            ),
            Thumbnail(
                imgUrl = "https://i.ytimg.com/vi/2iAd-tQVf4c/hqdefault.jpg",
                quality = 480.toString()
            )
        )
    )
    YoutubePlayerScreen(youtubeData = data)
}