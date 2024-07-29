package com.kaleksandra.featuregamepad.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.featuregamepad.presentation.ui.LoadingAnimation
import com.kaleksandra.featuregamepad.presentation.ui.GamepadButtons

@Composable
fun ControllerScreen(navController: NavController, viewModel: GamepadViewModel = hiltViewModel()) {
    val image by viewModel.imageState.collectAsState(null)
    val frames by viewModel.frameState.collectAsState()
    val ping by viewModel.pingState.collectAsState()
    BackHandler {}
    ControllerScreen(
        image,
        frames,
        ping,
        viewModel::sendAction,
    )
}

@Composable
fun ControllerScreen(
    image: ImageBitmap?,
    frames: Int,
    ping: Long,
    sendAction: (String) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val buttonSize = 18.dp
    val buttonSizeInt = 18 * 2
    val screenHeight = (configuration.screenHeightDp / buttonSizeInt * buttonSizeInt).dp
    val screenWidth = (configuration.screenWidthDp / buttonSizeInt * buttonSizeInt).dp
    Row(horizontalArrangement = Arrangement.spacedBy(Dimen.padding_20)) {
        Box(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .padding(Dimen.padding_8)
                    .clip(RoundedCornerShape(Dimen.radius_20))
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(Dimen.padding_4),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingAnimation()
                    Text(
                        text = "Загрузка",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                var size by remember { mutableStateOf(IntSize.Zero) }
                image?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .onGloballyPositioned { coordinates ->
                                size = coordinates.size
                            }
                    )
                }
                Text(
                    text = frames.toString(),
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .align(Alignment.BottomStart)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .align(Alignment.BottomEnd),
                    horizontalArrangement = Arrangement.spacedBy(Dimen.padding_12),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val color = when {
                        ping > 300L -> Color.Red
                        ping > 100L -> Color.Yellow
                        ping > 0L -> Color.Green
                        else -> Color.White
                    }
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(color, CircleShape)
                    ) {}
                    Text(
                        text = ping.toString(),
                        color = color
                    )
                }
            }
            Box(
                modifier = Modifier
                    .width(screenWidth)
                    .height(screenHeight)
                    .align(Alignment.Center)
            ) {
                GamepadButtons(buttonSize * 2, sendAction, sendAction)
            }
        }
    }
}