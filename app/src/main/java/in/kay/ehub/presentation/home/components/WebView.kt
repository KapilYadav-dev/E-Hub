package `in`.kay.ehub.presentation.home.components

import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(viewModel: HomeViewModel) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled=true
            settings.allowFileAccess=true
            settings.allowContentAccess=true
            settings.allowFileAccessFromFileURLs=true
            settings.allowUniversalAccessFromFileURLs=true
            settings.domStorageEnabled=true
            settings.setSupportZoom(true)
            settings.builtInZoomControls=true
            settings.displayZoomControls=false
            settings.useWideViewPort=true
            settings.loadWithOverviewMode=true

            loadUrl(viewModel.url.value)
        }
    }, update = {
        it.loadUrl(viewModel.url.value)
    })
}

