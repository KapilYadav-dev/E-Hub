package `in`.kay.ehub.presentation.auth.screens.splash

import `in`.kay.ehub.R
import `in`.kay.ehub.data.datastore.UserDatastore
import `in`.kay.ehub.presentation.navigation.auth.AuthNavRoutes
import android.app.StatusBarManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.solver.widgets.ConstraintWidget.ContentAlignment
import androidx.constraintlayout.solver.widgets.ConstraintWidget.INVISIBLE
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityManagerCompat
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var timerEnd by remember {
        mutableStateOf(false)
    }

//    val systemUiController = rememberSystemUiController()
//    systemUiController.setStatusBarColor(Color(0xFF138380))

    scope.launch {
        delay(6000L)
        timerEnd = true
    }

    if (timerEnd) {
        //Getting user loggedIn state using flow
        val user = UserDatastore(context).getUserLoggedIn()
        user.collectAsState(initial = null).value.let {
            LaunchedEffect(it) {
                when (it) {
                    true -> {
                        navController.navigate(AuthNavRoutes.Home.route)
                    }
                    false -> {
                        navController.navigate(AuthNavRoutes.Auth.route)
                    }
                    // This is the case when we don't have any use
                    null -> {}

                }
            }
        }
    }




        AndroidView(
            modifier = Modifier.fillMaxSize().background(Color(0xFF138380)),
            factory = {
            val view = LayoutInflater.from(it).inflate(R.layout.splash_screen_view,null,false)
            val videoView = view.findViewById<VideoView>(R.id.videoView1)

//            val mediaController = MediaController(it).apply { setAnchorView(videoView)}
            val uriPath = "android.resource://${it.packageName}/"+R.raw.ehub_log_animation
            val uri = Uri.parse(uriPath)

                videoView.setVideoURI(uri)
                videoView.setOnPreparedListener { mediaPlayer ->
                    val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
                    val screenRatio = videoView.width / videoView.height.toFloat()
                    val scaleX = videoRatio / screenRatio
                    if (scaleX >= 1f) {
                        videoView.scaleX = scaleX
                    } else {
                        videoView.scaleY = 1f / scaleX
                    }
                }

            videoView.start()

            view
        })

    }

@Composable
fun VideoPlayer() {
    // This is the official way to access current context from Composable functions
    val context = LocalContext.current

    // Do not recreate the player everytime this Composable commits
    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build().apply {
//            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context,
//                Util.getUserAgent(context, context.packageName))

//            val source = ExtractorMediaSource(dataSourceFactory)
//                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.ehub_log_animation))

            val uri = RawResourceDataSource.buildRawResourceUri(R.raw.ehub_log_animation)
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
        }
    }
    AndroidView(modifier = Modifier.fillMaxSize(),
        factory = {
        PlayerControlView(it).apply {
            player = exoPlayer
            visibility = PlayerControlView.INVISIBLE
        }
    })
//    AndroidView(
//        viewBlock = { context ->
//            PlayerControlView(context).apply {
//                player = exoPlayer
//                playWhenReady = true
//            }
//        }
//    )
}
@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.splash).apply(block = {
                size(Size.ORIGINAL)
            }).build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
    )
}


//val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.demo3))
//val logoAnimationState =
//    animateLottieCompositionAsState(composition = composition, speed = 1.5f)
//LottieAnimation(
//composition = composition,
//progress = { logoAnimationState.progress },
//contentScale = ContentScale.FillWidth,
//modifier = Modifier
//.size(250.dp)
//.fillMaxWidth()
//)
//if(logoAnimationState.isAtEnd && logoAnimationState.isPlaying){
//    if(resultBool.value){
//        navController.navigate(AuthNavRoutes.Home.route)
//    }else{
//        navController.navigate(AuthNavRoutes.Auth.route)
//    }
//}